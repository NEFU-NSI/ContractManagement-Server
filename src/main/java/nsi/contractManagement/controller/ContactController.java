package nsi.contractManagement.controller;

import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import nsi.contractManagement.DO.ContractDO;
import nsi.contractManagement.DTO.PageDTO;
import nsi.contractManagement.DTO.StatisticsDTO;
import nsi.contractManagement.config.ResponseResultBody;
import nsi.contractManagement.mapper.ContractMapper;
import nsi.contractManagement.service.ContractService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


/**
 * @Author: Tao
 * @Time: 2020/10/8 19:19
 * @ProjectName: contract-management
 * @FileName: ContactController.java
 * @IDE: IntelliJ IDEA
 */
@RestController
@Api("合同增查改删操作")
@RequestMapping("contract")
@ResponseResultBody
public class ContactController {
    @Autowired
    private ContractService contractService;

    @Autowired
    private ContractMapper contractMapper;

    @PostMapping("add")
    @ApiOperation(value = "添加合同信息")
    public boolean addContract(@RequestBody ContractDO contractDO) {
        UpdateWrapper<ContractDO> updateWrapper = new UpdateWrapper<>();
        updateWrapper.eq("contract_number", contractDO.getContractNumber()).or().eq("name",
                contractDO.getName());
        return contractService.saveOrUpdate(contractDO, updateWrapper);
    }


    @GetMapping("list")
    @ApiOperation(value = "查询合同信息")
    public PageDTO<ContractDO> listContract(
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
                    defaultValue = "5") Integer size

    ) {
        Page<ContractDO> pageContractDO = new Page<>(current, size);
        return new PageDTO<>(
                contractService.multipleConditionsSearch(year, company, contractName,
                        contractDepartment,
                        current, size),
                pageContractDO);
    }

    @GetMapping("statistics")
    @ApiOperation("根据年份、科室统计")
    public List<StatisticsDTO> statisticsByYearOrDepartment(@RequestParam(value = "year") String year) {
        return contractMapper.statisticsMapper(year);
    }

}
