package jiaruchun.service.payservice.pojo.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Data;


@Data
@Builder
@ApiModel(description = "支付确认表单实体")
public class PayOrderFormDTO {
    @ApiModelProperty("支付订单id不能为空")
    @NotNull(message = "支付订单id不能为空")
    private Long id;
    @ApiModelProperty("支付密码")
    @NotNull(message = "支付密码")
    private String pw;
}