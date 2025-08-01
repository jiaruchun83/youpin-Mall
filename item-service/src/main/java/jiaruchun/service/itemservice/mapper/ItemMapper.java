package jiaruchun.service.itemservice.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import jiaruchun.service.itemservice.pojo.dto.OrderDetailDTO;
import jiaruchun.service.itemservice.pojo.entity.Item;
import org.apache.ibatis.annotations.Update;

/**
 * <p>
 * 商品表 Mapper 接口
 * </p>
 *
 * @author 虎哥
 * @since 2023-05-05
 */
public interface ItemMapper extends BaseMapper<Item> {

    @Update("UPDATE item SET stock = stock - #{num} WHERE id = #{itemId}")
    void updateStock(OrderDetailDTO orderDetail);
}
