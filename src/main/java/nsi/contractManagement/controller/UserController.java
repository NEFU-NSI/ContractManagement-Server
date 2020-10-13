package nsi.contractManagement.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import nsi.contractManagement.DO.UserDO;
import nsi.contractManagement.VO.UserVO;
import nsi.contractManagement.config.response.ApiException;
import nsi.contractManagement.config.response.ResponseResultBody;
import nsi.contractManagement.config.response.Result;
import nsi.contractManagement.config.response.ResultStatus;
import nsi.contractManagement.mapper.DepartmentMapper;
import nsi.contractManagement.mapper.UserMapper;
import nsi.contractManagement.service.impl.CustomUserDetailsServiceImpl;
import nsi.contractManagement.service.impl.MyRemindServiceImpl;
import nsi.contractManagement.utils.JwtTokenUtil;
import nsi.contractManagement.utils.UserUtil;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

/**
 * @Author: Tao
 * @Time: 2020/10/11 14:14
 * @ProjectName: contract-management
 * @FileName: UserController.java
 * @IDE: IntelliJ IDEA
 */
@Api(tags = "用户接口")
@RequestMapping("/api/user")
@ResponseResultBody
@RestController
public class UserController {
    @Value("${jwt.tokenHeader}")
    private String tokenHeader = "Authorization";
    @Value("${jwt.tokenHead}")
    private String tokenHead = "Bearer ";

    @Autowired
    private MyRemindServiceImpl myRemindService;

    @Autowired
    private UserMapper userMapper;
    @Autowired
    private DepartmentMapper departmentMapper;

    @Autowired
    private CustomUserDetailsServiceImpl customUserDetailsServiceImpl;

    @Autowired
    private UserUtil userUtil;

    @ApiOperation(value = "注册")
    @PostMapping("register")
    public boolean register(@RequestParam String email,
                            @RequestParam String password,
                            @RequestParam String name
    ) {
        // TODO 科室存在性校验
        return customUserDetailsServiceImpl.register(email, password, name);
    }

    @ApiOperation(value = "登录")
    @PostMapping("login")
    public Result<Map<String, String>> login(@RequestParam String email,
                                             @RequestParam String password) {
        myRemindService.scanAndSave();
        String token = customUserDetailsServiceImpl.login(email, password);
        Map<String, String> tokenMap = new HashMap<>(10);
        tokenMap.put("token", token);
        tokenMap.put("tokenHead", tokenHead);
        return Result.success(tokenMap);
    }

    @ApiOperation(value = "获取用户信息")
    @GetMapping("info")
    public UserVO info(HttpServletRequest request) {
        return userUtil.info(request);
    }

    @ApiOperation(value = "刷新token")
    @GetMapping("refresh")
    public Result<Object> refreshToken(HttpServletRequest request) {
        String token = request.getHeader(tokenHeader);
        String refreshToken = customUserDetailsServiceImpl.refreshToken(token);
        if (refreshToken == null) {
            return Result.failure(ResultStatus.BAD_REQUEST, "token已经过期");
        }
        Map<String, String> tokenMap = new HashMap<>(10);
        tokenMap.put("token", refreshToken);
        tokenMap.put("tokenHead", tokenHead);
        return Result.success(ResultStatus.SUCCESS, tokenMap);
    }

    @ApiOperation("修改密码")
    @PostMapping("updatePassword")
    public boolean updatePassword(@RequestParam(value = "email", defaultValue = "") String email,
                                  @RequestParam(value = "password", defaultValue = "") String password,
                                  HttpServletRequest request) {
        JwtTokenUtil jwtTokenUtil = new JwtTokenUtil();
        String token = request.getHeader(tokenHeader).replace("Bearer ", "");
        boolean tokenExpired = jwtTokenUtil.isTokenExpired(token);
        if (!tokenExpired) {
            customUserDetailsServiceImpl.updatePassword(email, password);
        } else {
            throw new ApiException("token无效");
        }
        return customUserDetailsServiceImpl.updatePassword(email, password);
    }
}
