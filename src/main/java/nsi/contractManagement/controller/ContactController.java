package nsi.contractManagement.controller;

import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import nsi.contractManagement.DO.ContractDO;
import nsi.contractManagement.DO.DepartmentDO;
import nsi.contractManagement.VO.PageVO;
import nsi.contractManagement.VO.StatisticsVO;
import nsi.contractManagement.config.response.ApiException;
import nsi.contractManagement.config.response.ResponseResultBody;
import nsi.contractManagement.mapper.ContractMapper;
import nsi.contractManagement.service.ContractService;
import nsi.contractManagement.service.DepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;


/**
 * @Author: Tao
 * @Time: 2020/10/8 19:19
 * @ProjectName: contract-management
 * @FileName: ContactController.java
 * @IDE: IntelliJ IDEA
 */
@Slf4j
@RestController
@Api("合同增查改删操作")
@RequestMapping("/api/contract")
@ResponseResultBody
public class ContactController {
    @Autowired
    private ContractService contractService;

    @Autowired
    private ContractMapper contractMapper;

    @Autowired
    private DepartmentService departmentService;

    @PostMapping("add")
    @ApiOperation(value = "添加合同信息")
    public boolean addContract(@Valid @RequestBody ContractDO contractDO)  {
        UpdateWrapper<ContractDO> updateWrapper = new UpdateWrapper<>();
        DepartmentDO byId = departmentService.getById(contractDO.getDepartment());
        if (byId == null) {
            throw new ApiException("部门不存在");
        }
        updateWrapper.eq("contract_number", contractDO.getContractNumber()).or().eq("name",
                contractDO.getName());
        return contractService.saveOrUpdate(contractDO, updateWrapper);
    }


    @GetMapping("list")
    @ApiOperation(value = "查询合同信息")
    public PageVO<ContractDO> listContract(
            @RequestParam(value = "signYear", required = false,
                    defaultValue = "") String year,
            @RequestParam(value = "company", required = false,
                    defaultValue = "") String company,
            @RequestParam(value = "contractName", required = false,
                    defaultValue = "") String contractName,
            @RequestParam(value = "contractDepartment", required =
                    false,
                    defaultValue = "") String contractDepartment,
            @RequestParam(value = "current",
                    defaultValue = "1") Integer current,
            @RequestParam(value = "size",
                    defaultValue = "3") Integer size

    ) {
        Page<ContractDO> pageContractDO = new Page<>(current, size);
        long multipleConditionsSearchTotal = contractMapper.multipleConditionsSearchTotal(year,
                company, contractName,
                contractDepartment);
        List<ContractDO> contractDos = contractMapper.multipleConditionsSearch(pageContractDO,
                year, company, contractName,
                contractDepartment);
        return new PageVO<>(current,size,multipleConditionsSearchTotal,contractDos);
    }

    @GetMapping("statistics")
    @ApiOperation("根据年份、科室统计")
    public List<StatisticsVO> statisticsByYearOrDepartment(@RequestParam(value = "year") String year) {
        return contractMapper.statisticsMapper(year);
    }

}
