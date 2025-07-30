package jiaruchun.service.tradeservice.service.imp;

import cn.hutool.core.util.IdUtil;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.hmall.common.exception.BadRequestException;
import com.hmall.common.utils.ThreadLocalUtil;
import io.seata.spring.annotation.GlobalTransactional;
import jiaruchun.api.openfeignclient.cart.CartOpenFeignApi;
import jiaruchun.api.openfeignclient.item.ItemOpenFeignApi;
import jiaruchun.api.pojo.dto.ItemDTO;
import jiaruchun.api.pojo.dto.OrderDetailDTO;
import jiaruchun.service.tradeservice.mapper.OrderMapper;
import jiaruchun.service.tradeservice.pojo.dto.OrderFormDTO;
import jiaruchun.service.tradeservice.pojo.entity.Order;
import jiaruchun.service.tradeservice.pojo.entity.OrderDetail;
import jiaruchun.service.tradeservice.service.IOrderDetailService;
import jiaruchun.service.tradeservice.service.IOrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author 虎哥
 * @since 2023-05-05
 */
@Service
@RequiredArgsConstructor
public class OrderServiceImpl extends ServiceImpl<OrderMapper, Order> implements IOrderService {

    private final IOrderDetailService iOrderService;
    private final ItemOpenFeignApi itemOpenFeignApi;
    private final CartOpenFeignApi cartOpenFeignApi;

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @Override
    @GlobalTransactional
    public Long createOrder(OrderFormDTO orderFormDTO) {

        long orderId = IdUtil.getSnowflake().nextId();

        HashMap<String, Object> data = new HashMap<>();
        data.put("userId",ThreadLocalUtil.get());
        data.put("orderId",orderId);
        data.put("orderFormDTO",orderFormDTO);
        rabbitTemplate.convertAndSend("mall.order.fanoutExchange","",data);
        return orderId;
    }

    @RabbitListener(queues = "mall.order.fanoutExchange.queue1")
    public void createOrderByMQ(Object map){
        if (!(map instanceof Map<?, ?>)) {
            throw new BadRequestException("接收到的消息不是 Map 类型");
        }
        HashMap<String, Object> date = (HashMap<String, Object>) map;
        Long userId = (Long) date.get("userId");
        Long orderId = (Long) date.get("orderId");
        OrderFormDTO orderFormDTO = (OrderFormDTO) date.get("orderFormDTO");
        // 1.订单数据
        Order order = new Order();
        order.setId(orderId);
        // 1.1.查询商品
        List<OrderDetailDTO> detailDTOS = orderFormDTO.getDetails();
        // 1.2.获取商品id和数量的Map
        Map<Long, Integer> itemNumMap = detailDTOS.stream()
                .collect(Collectors.toMap(OrderDetailDTO::getItemId, OrderDetailDTO::getNum));
        Set<Long> itemIds = itemNumMap.keySet();
        // 1.3.查询商品
        List<ItemDTO> items = itemOpenFeignApi.queryItemByIds(itemIds);
        if (items == null || items.size() < itemIds.size()) {
            throw new BadRequestException("商品不存在");
        }
        // 1.4.基于商品价格、购买数量计算商品总价：totalFee
        int total = 0;
        for (ItemDTO item : items) {
            total += item.getPrice() * itemNumMap.get(item.getId());
        }
        order.setTotalFee(total);
        // 1.5.其它属性
        order.setPaymentType(orderFormDTO.getPaymentType());
        order.setUserId(userId);
        order.setStatus(1);
        // 1.6.将Order写入数据库order表中
        save(order);

        // 2.保存订单详情
        List<OrderDetail> details = buildDetails(order.getId(), items, itemNumMap);
        iOrderService.saveBatch(details);

        // 3.清理购物车商品
        cartOpenFeignApi.deleteCartItemByIds(itemIds);

        // 4.扣减库存
        try {
            itemOpenFeignApi.deductStock(detailDTOS);
        } catch (Exception e) {
            throw new RuntimeException("库存不足！");
        }
    }

    @Override
    public void markOrderPaySuccess(Long orderId) {
        Order order = new Order();
        order.setId(orderId);
        order.setStatus(2);
        order.setPayTime(LocalDateTime.now());
        updateById(order);
    }

    private List<OrderDetail> buildDetails(Long orderId, List<ItemDTO> items, Map<Long, Integer> numMap) {
        List<OrderDetail> details = new ArrayList<>(items.size());
        for (ItemDTO item : items) {
            OrderDetail detail = new OrderDetail();
            detail.setName(item.getName());
            detail.setSpec(item.getSpec());
            detail.setPrice(item.getPrice());
            detail.setNum(numMap.get(item.getId()));
            detail.setItemId(item.getId());
            detail.setImage(item.getImage());
            detail.setOrderId(orderId);
            details.add(detail);
        }
        return details;
    }
}
