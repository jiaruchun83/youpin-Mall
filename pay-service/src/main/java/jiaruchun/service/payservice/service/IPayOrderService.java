package jiaruchun.service.payservice.service;

import com.baomidou.mybatisplus.extension.service.IService;
import jiaruchun.service.payservice.pojo.dto.PayApplyDTO;
import jiaruchun.service.payservice.pojo.dto.PayOrderFormDTO;
import jiaruchun.service.payservice.pojo.entity.PayOrder;


/**
 * <p>
 * 支付订单 服务类
 * </p>
 *
 * @author 虎哥
 * @since 2023-05-16
 */
public interface IPayOrderService extends IService<PayOrder> {

    String applyPayOrder(PayApplyDTO applyDTO);

    void tryPayOrderByBalance(PayOrderFormDTO payOrderFormDTO);
}
