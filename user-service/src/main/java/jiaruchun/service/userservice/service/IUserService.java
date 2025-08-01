package jiaruchun.service.userservice.service;

import com.baomidou.mybatisplus.extension.service.IService;
import jiaruchun.service.userservice.pojo.dto.LoginFormDTO;
import jiaruchun.service.userservice.pojo.entity.User;
import jiaruchun.service.userservice.pojo.vo.UserLoginVO;


/**
 * <p>
 * 用户表 服务类
 * </p>
 *
 * @author 虎哥
 * @since 2023-05-05
 */
public interface IUserService extends IService<User> {

    UserLoginVO login(LoginFormDTO loginFormDTO);

    void deductMoney(String pw, Integer totalFee);
}
