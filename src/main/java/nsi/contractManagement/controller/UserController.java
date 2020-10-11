package nsi.contractManagement.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import nsi.contractManagement.DO.DepartmentDO;
import nsi.contractManagement.DO.UserDO;
import nsi.contractManagement.config.response.ApiException;
import nsi.contractManagement.config.response.ResponseResultBody;
import nsi.contractManagement.mapper.DepartmentMapper;
import nsi.contractManagement.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

/**
 * @Author: Tao
 * @Time: 2020/10/11 14:14
 * @ProjectName: contract-management
 * @FileName: UserController.java
 * @IDE: IntelliJ IDEA
 */
@Api(value = "用户接口")
@RequestMapping("/api/user")
@ResponseResultBody
@RestController
public class UserController {
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private DepartmentMapper departmentMapper;

    @ApiOperation(value = "注册")
    @PostMapping("register")
    public boolean register(@Valid @RequestBody UserDO userDO) {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        String encoderPassword = encoder.encode(userDO.getPassword());
        userDO.setPassword(encoderPassword);
        QueryWrapper<UserDO> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("email", userDO.getEmail()).or().eq("name", userDO.getName());
        UserDO selectOne = userMapper.selectOne(queryWrapper);
        if (selectOne != null) {
            throw new ApiException("重复注册");
        }
        DepartmentDO departmentDO = departmentMapper.selectById(userDO.getDepartment());
        if (departmentDO == null) {
            throw new ApiException("部门不存在");
        }
        return userMapper.insert(userDO) == 1;
    }
}
