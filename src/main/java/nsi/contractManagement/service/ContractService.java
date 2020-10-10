package nsi.contractManagement.service;

import com.baomidou.mybatisplus.extension.service.IService;
import nsi.contractManagement.DO.ContractDO;
import org.apache.ibatis.annotations.Param;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

/**
 * @author Tao
 */
public interface ContractService extends IService<ContractDO> {

    /**
     * 多条件查询合同
     *
     * @param year               年份
     * @param company            公司
     * @param contractName       合同名称
     * @param contractDepartment 合同单位
     * @param current            当前页
     * @param size               页面大小
     * @return 符合条件的合同
     */
    List<ContractDO> multipleConditionsSearch(@Param("signYear") String year,
                                              @Param("company") String company,
                                              @Param("contractName") String contractName,
                                              @Param("contractDepartment") String contractDepartment,
                                              @Param("current") Integer current,
                                              @Param("size") Integer size
    );

    /**
     * 多条件查询合同
     *
     * @param year               年份
     * @param company            公司
     * @param contractName       合同名称
     * @param contractDepartment 合同单位
     * @param current            当前页
     * @param size               页面大小
     * @return 符合条件的合同
     */
    int multipleConditionsSearchTotal(@Param("signYear") String year,
                                      @Param("company") String company,
                                      @Param("contractName") String contractName,
                                      @Param("contractDepartment") String contractDepartment,
                                      @Param("current") Integer current,
                                      @Param("size") Integer size
    );
}
