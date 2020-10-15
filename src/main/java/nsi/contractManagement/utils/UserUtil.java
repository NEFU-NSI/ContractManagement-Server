package nsi.contractManagement.utils;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import nsi.contractManagement.DO.UserDO;
import nsi.contractManagement.DTO.LoginUser;
import nsi.contractManagement.VO.UserVO;
import nsi.contractManagement.config.response.ApiException;
import nsi.contractManagement.mapper.DepartmentMapper;
import nsi.contractManagement.mapper.UserMapper;
import nsi.contractManagement.service.impl.CustomUserDetailsServiceImpl;
import nsi.contractManagement.service.impl.MyRemindServiceImpl;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
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

    public UserDO getUserDo(String email) {
        return userMapper.selectOne(new QueryWrapper<UserDO>().eq("email", email));

    }

    public UserVO info(String email) {
        UserVO userVO = new UserVO();
        UserDO currentMember = this.getUserDo(email);
        BeanUtils.copyProperties(currentMember, userVO);
        String departmentName =
                departmentMapper.selectById(currentMember.getId()).getDepartmentName();
        userVO.setDepartment(departmentName);
        return userVO;
    }

    public int getUserDepartment(String email) {
        return this.getUserDo(email).getDepartment();
    }

    public UserDO getCurrentMember() {
        SecurityContext ctx = SecurityContextHolder.getContext();
        Authentication auth = ctx.getAuthentication();
        LoginUser memberDetails = (LoginUser) auth.getPrincipal();
        return memberDetails.getUser();
    }
}
