package jiaruchun.service.tradeservice.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import jiaruchun.service.tradeservice.pojo.entity.Order;
import org.apache.ibatis.annotations.Update;


/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author 虎哥
 * @since 2023-05-05
 */
public interface OrderMapper extends BaseMapper<Order> {

    @Update("update order set status = 5,update_time = NOW(),close_time = NOW() where id = #{orderId1} and status = 1")
    void updateStatus(Long orderId1);
}
