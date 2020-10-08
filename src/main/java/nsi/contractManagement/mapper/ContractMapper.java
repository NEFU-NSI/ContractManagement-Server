package nsi.contractManagement.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import nsi.contractManagement.DO.Contract;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author Tao
 */
@Mapper
public interface ContractMapper extends BaseMapper<Contract> {
}