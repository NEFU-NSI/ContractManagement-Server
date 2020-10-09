package nsi.contractManagement.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import nsi.contractManagement.DO.ContractDO;
import nsi.contractManagement.config.ResponseResultBody;
import nsi.contractManagement.mapper.ContractMapper;
import nsi.contractManagement.service.ContractService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Calendar;
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
        return contractService.save(contractDO);
    }


    @GetMapping("list")
    @ApiOperation(value = "查询合同信息")
    public List<ContractDO> listContract(
            @RequestParam(value = "signYear", required = false,
                    defaultValue = "") String year,
            @RequestParam(value = "company", required = false,
                    defaultValue = "") String company,
            @RequestParam(value = "contractName", required = false,
                    defaultValue = "") String contractName,
            @RequestParam(value = "contractDepartment", required =
                    false,
                    defaultValue = "") String contractDepartment,
            @RequestParam(value = "page",
                    defaultValue = "1") Integer page,
            @RequestParam(value = "size",
                    defaultValue = "5") Integer size

    ) {

        Page<ContractDO> pageContractDO = new Page<ContractDO>(page, size);
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
        return contractMapper.selectPage(pageContractDO, queryWrapper).getRecords();
    }


}
