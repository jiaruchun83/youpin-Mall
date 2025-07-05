package jiaruchun.service.payservice.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import jiaruchun.api.pojo.entity.Order;
import jiaruchun.service.payservice.pojo.entity.PayOrder;
import org.apache.ibatis.annotations.Update;


/**
 * <p>
 * 支付订单 Mapper 接口
 * </p>
 *
 * @author 虎哥
 * @since 2023-05-16
 */
public interface PayOrderMapper extends BaseMapper<PayOrder> {

    @Update("update pay_order set status = 2, pay_time = now() where id = #{id}")
    void updateById(Order order);
}
