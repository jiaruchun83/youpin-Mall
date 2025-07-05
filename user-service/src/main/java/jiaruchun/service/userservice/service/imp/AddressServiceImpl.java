package jiaruchun.service.userservice.service.imp;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import jiaruchun.service.userservice.mapper.AddressMapper;
import jiaruchun.service.userservice.pojo.entity.Address;
import jiaruchun.service.userservice.service.IAddressService;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author 虎哥
 * @since 2023-05-05
 */
@Service
public class AddressServiceImpl extends ServiceImpl<AddressMapper, Address> implements IAddressService {

}
