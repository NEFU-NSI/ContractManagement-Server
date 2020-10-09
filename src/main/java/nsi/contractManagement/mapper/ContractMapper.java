package nsi.contractManagement.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import nsi.contractManagement.DO.ContractDO;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author Tao
 */
@Mapper
public interface ContractMapper extends BaseMapper<ContractDO> {
}