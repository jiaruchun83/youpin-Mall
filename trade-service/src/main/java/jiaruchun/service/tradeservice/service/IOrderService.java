package jiaruchun.service.tradeservice.service;

import com.baomidou.mybatisplus.extension.service.IService;
import jiaruchun.service.tradeservice.pojo.dto.OrderFormDTO;
import jiaruchun.service.tradeservice.pojo.entity.Order;


/**
 * <p>
 *  服务类
 * </p>
 *
 * @author 虎哥
 * @since 2023-05-05
 */
public interface IOrderService extends IService<Order> {

    Long createOrder(OrderFormDTO orderFormDTO);

    void markOrderPaySuccess(Long orderId);
}
