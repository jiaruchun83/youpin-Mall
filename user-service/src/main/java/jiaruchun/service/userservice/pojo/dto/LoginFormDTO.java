package jiaruchun.service.userservice.pojo.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;



@Data
@ApiModel(description = "登录表单实体")
public class LoginFormDTO {
    @ApiModelProperty(value = "用户名", required = true)
    @NotNull(message = "用户名不能为空")
    private String username;
    @NotNull(message = "密码不能为空")
    @ApiModelProperty(value = "用户名", required = true)
    private String password;
    @ApiModelProperty(value = "是否记住我", required = false)
    private Boolean rememberMe = false;
}
