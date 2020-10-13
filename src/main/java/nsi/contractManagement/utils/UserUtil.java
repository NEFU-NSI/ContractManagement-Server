package nsi.contractManagement.utils;

import nsi.contractManagement.DO.UserDO;
import nsi.contractManagement.VO.UserVO;
import nsi.contractManagement.config.response.ApiException;
import nsi.contractManagement.mapper.DepartmentMapper;
import nsi.contractManagement.mapper.UserMapper;
import nsi.contractManagement.service.impl.CustomUserDetailsServiceImpl;
import nsi.contractManagement.service.impl.MyRemindServiceImpl;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;

/**
 * @Author: Tao
 * @Time: 2020/10/13 15:48
 * @ProjectName: contract-management
 * @FileName: UserUtil.java
 * @IDE: IntelliJ IDEA
 */
@Component
public class UserUtil {
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

    public UserDO getUserDo(HttpServletRequest request) {
        JwtTokenUtil jwtTokenUtil = new JwtTokenUtil();
        String token = request.getHeader(tokenHeader).replace("Bearer ", "");
        boolean tokenExpired = jwtTokenUtil.isTokenExpired(token);
        if (!tokenExpired) {
            return customUserDetailsServiceImpl.getCurrentMember(token);
        } else {
            throw new ApiException("用户token失效");
        }
    }

    public UserVO info(HttpServletRequest request) {
        UserVO userVO = new UserVO();
        UserDO currentMember = this.getUserDo(request);
        BeanUtils.copyProperties(currentMember, userVO);
        String departmentName =
                departmentMapper.selectById(currentMember.getId()).getDepartmentName();
        userVO.setDepartment(departmentName);
        return userVO;
    }

    public int getUserDepartment(HttpServletRequest request) {
        return this.getUserDo(request).getDepartment();
    }

}
