package nsi.contractManagement.service.impl;

import nsi.contractManagement.DO.UserDO;
import org.springframework.stereotype.Service;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import nsi.contractManagement.mapper.UserMapper;
import nsi.contractManagement.service.UserService;
/**
 * @author Tao
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, UserDO> implements UserService{

}
