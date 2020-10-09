package nsi.contractManagement.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
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
    @Select("select sum(amount) as amount, department.department_name\n" +
            "from contract,\n" +
            "     department\n" +
            "where contract.department = department.id\n" +
            "  and year(sign) = #{year}\n" +
            "group by department\n" +
            "order by amount desc;")
    List<StatisticsDTO> statisticsMapper(@Param("year") String year);
}