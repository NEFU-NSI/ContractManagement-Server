package nsi.contractManagement.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import nsi.contractManagement.DO.UserDO;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author Tao
 */
@Mapper
public interface UserMapper extends BaseMapper<UserDO> {
}