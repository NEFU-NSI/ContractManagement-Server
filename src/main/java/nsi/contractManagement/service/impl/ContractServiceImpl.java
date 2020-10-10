package nsi.contractManagement.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import nsi.contractManagement.DO.ContractDO;
import org.springframework.stereotype.Service;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import nsi.contractManagement.mapper.ContractMapper;
import nsi.contractManagement.service.ContractService;

import java.time.LocalDateTime;
import java.util.Calendar;
import java.util.List;

/**
 * @author Tao
 */
@Service
public class ContractServiceImpl extends ServiceImpl<ContractMapper, ContractDO> implements ContractService {
    private final ContractMapper contractMapper;

    public ContractServiceImpl(ContractMapper contractMapper) {
        this.contractMapper = contractMapper;
    }


    @Override
    public List<ContractDO> multipleConditionsSearch(String year, String company,
                                                     String contractName,
                                                     String contractDepartment, Integer current,
                                                     Integer size) {
        Page<ContractDO> pageContractDO = new Page<>(current, size);
        initQueryWrapper(year, company, contractName, contractDepartment);
        QueryWrapper<ContractDO> queryWrapper = initQueryWrapper(year, company, contractName,
                contractDepartment);
        return contractMapper.selectPage(pageContractDO, queryWrapper).getRecords();
    }

    @Override
    public int multipleConditionsSearchTotal(String year, String company, String contractName,
                                             String contractDepartment, Integer current,
                                             Integer size) {
        QueryWrapper<ContractDO> queryWrapper = initQueryWrapper(year, company, contractName,
                contractDepartment);
        return contractMapper.selectCount(queryWrapper);
    }

    private QueryWrapper<ContractDO> initQueryWrapper(String year, String company,
                                                      String contractName,
                                                      String contractDepartment) {
        QueryWrapper<ContractDO> queryWrapper = new QueryWrapper<>();
        if ("".equals(year)) {
            Calendar calendar = Calendar.getInstance();
            year = String.valueOf(calendar.get(Calendar.YEAR));
        }
        queryWrapper.between("sign", LocalDateTime.of(Integer.parseInt(year), 1, 1, 0, 0, 0),
                LocalDateTime.of(Integer.parseInt(year),
                        12, 31, 23, 59, 59));
        if (!"".equals(company)) {
            queryWrapper.eq("company", company);
        }
        if (!"".equals(contractName)) {
            queryWrapper.eq("name", contractName);
        }
        if (!"".equals(contractName)) {
            queryWrapper.eq("department", contractDepartment);
        }
        return queryWrapper;
    }

}
