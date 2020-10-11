package nsi.contractManagement.controller;

import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import nsi.contractManagement.DO.DepartmentDO;
import nsi.contractManagement.VO.DepartmentVO;
import nsi.contractManagement.config.response.ResponseResultBody;
import nsi.contractManagement.service.DepartmentService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @Author: Tao
 * @Time: 2020/10/10 18:25
 * @ProjectName: contract-management
 * @FileName: DepartmentController.java
 * @IDE: IntelliJ IDEA
 */
@Slf4j
@Api("部门接口")
@RestController
@ResponseResultBody
@RequestMapping("/api/department")
public class DepartmentController {
    @Autowired
    private DepartmentService departmentService;

    @ApiOperation(value = "新增部门")
    @PostMapping("add")
    public boolean addDepartment(@Validated @RequestBody DepartmentVO departmentVO) {
        DepartmentDO departmentDO = new DepartmentDO();
        BeanUtils.copyProperties(departmentVO,departmentDO);
        return departmentService.save(departmentDO);
    }

    @ApiOperation(value = "删除部门")
    @DeleteMapping("delete")
    public boolean deleteDepartment(@RequestParam(value = "departmentId") String departmentId) {
        return departmentService.removeById(departmentId);
    }

    @ApiOperation(value = "更新部门")
    @PutMapping("update")
    public boolean updateDepartment(@RequestParam(value = "departmentId") String departmentId,
                                    @Validated @RequestBody DepartmentVO departmentVO) {
        UpdateWrapper<DepartmentDO> updateWrapper = new UpdateWrapper<>();
        updateWrapper.eq("id", departmentId);
        DepartmentDO departmentDO = new DepartmentDO();
        BeanUtils.copyProperties(departmentVO,departmentDO);
        return departmentService.update(departmentDO, updateWrapper);
    }

    @ApiOperation(value = "展示所有部门")
    @GetMapping("all")
    public List<DepartmentDO> listDepartment() {
        return departmentService.list();
    }
}
