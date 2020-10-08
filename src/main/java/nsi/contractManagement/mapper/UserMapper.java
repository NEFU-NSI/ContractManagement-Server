package nsi.contractManagement.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import nsi.contractManagement.DO.User;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author Tao
 */
@Mapper
public interface UserMapper extends BaseMapper<User> {
}