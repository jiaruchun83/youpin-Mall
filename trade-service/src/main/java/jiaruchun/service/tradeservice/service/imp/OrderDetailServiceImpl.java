package jiaruchun.service.tradeservice.service.imp;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import jiaruchun.service.tradeservice.mapper.OrderDetailMapper;
import jiaruchun.service.tradeservice.pojo.entity.OrderDetail;
import jiaruchun.service.tradeservice.service.IOrderDetailService;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 订单详情表 服务实现类
 * </p>
 *
 * @author 虎哥
 * @since 2023-05-05
 */
@Service
public class OrderDetailServiceImpl extends ServiceImpl<OrderDetailMapper, OrderDetail> implements IOrderDetailService {

}
