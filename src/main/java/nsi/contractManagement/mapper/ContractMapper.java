package nsi.contractManagement.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import nsi.contractManagement.DO.ContractDO;
import nsi.contractManagement.DTO.StatisticsDTO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.context.annotation.Primary;

import java.time.Year;
import java.util.List;

/**
 * @author Tao
 */
@Mapper
public interface ContractMapper extends BaseMapper<ContractDO> {
    /**
     * 返回按大小排序的统计数据
     *
     * @param year 年份
     * @return Map<department, amount>
     */

    List<StatisticsDTO> statisticsMapper(@Param("year") String year);

    /**
     * 多条件查询合同
     *
     * @param page               分页插件
     * @param year               年份
     * @param company            公司
     * @param contractName       合同名称
     * @param contractDepartment 合同单位
     * @return 符合条件的合同
     */
    List<ContractDO> multipleConditionsSearch(Page<ContractDO> page, @Param("signYear") String year,
                                              @Param("company") String company,
                                              @Param("contractName") String contractName,
                                              @Param("contractDepartment") String contractDepartment
    );

    /**
     * 多条件查询合同数量
     *
     * @param year               年份
     * @param company            公司
     * @param contractName       合同名称
     * @param contractDepartment 合同单位
     * @return 符合条件的合同
     */
    long multipleConditionsSearchTotal(@Param("signYear") String year,
                                      @Param("company") String company,
                                      @Param("contractName") String contractName,
                                      @Param("contractDepartment") String contractDepartment
    );
}