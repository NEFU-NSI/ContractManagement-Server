package nsi.contractManagement.service.impl;

import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import io.swagger.models.auth.In;
import lombok.extern.slf4j.Slf4j;
import nsi.contractManagement.DO.UserDO;
import nsi.contractManagement.DTO.LoginUser;
import nsi.contractManagement.config.response.ApiException;
import nsi.contractManagement.mapper.UserMapper;
import nsi.contractManagement.utils.JwtTokenUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;
import java.util.List;

/**
 * @Author: Tao
 * @Time: 2020/10/11 11:01
 * @ProjectName: contract-management
 * @FileName: CustomUserDetailsService.java
 * @IDE: IntelliJ IDEA
 */
@Slf4j
@Service
public class CustomUserDetailsServiceImpl implements UserDetailsService {
    @Autowired
    private UserMapper userMapper;

    @Autowired
    private CustomUserDetailsServiceImpl customUserDetailsServiceImpl;

    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        if (StrUtil.isBlank(email)) {
            log.info("登录用户：{} 不存在", email);
            throw new UsernameNotFoundException("登录用户：" + email + " 不存在");
        }

        // 查出密码
        UserDO userDO = userMapper.selectOne(new QueryWrapper<UserDO>().eq("email", email));
        if (ObjectUtil.isNull(userDO) || StrUtil.isBlank(userDO.getName())) {
            log.info("登录用户：{} 不存在", email);
            throw new UsernameNotFoundException("登录用户：" + email + " 不存在");
        }
        return new LoginUser(userDO, "", LocalDateTime.now());
    }


    public String login(String email, String password) {
        String token = null;
        //密码需要客户端加密后传递
        try {
            UserDetails userDetails = customUserDetailsServiceImpl.loadUserByUsername(email);
            if (!passwordEncoder.matches(password, userDetails.getPassword())) {
                throw new ApiException("密码不正确");
            }
            UsernamePasswordAuthenticationToken authentication =
                    new UsernamePasswordAuthenticationToken(userDetails, null,
                            userDetails.getAuthorities());
            SecurityContextHolder.getContext().setAuthentication(authentication);
            token = jwtTokenUtil.generateToken(userDetails);
        } catch (AuthenticationException e) {
            log.warn("登录异常:{}", e.getMessage());
        }
        return token;
    }

    public boolean register(String email, String password, String name, Integer department) {

        List<UserDO> userDOList = userMapper.selectList(new QueryWrapper<UserDO>().eq("email",
                email).or().eq("name"
                , name));
        if (!CollectionUtils.isEmpty(userDOList)) {
            throw new ApiException("用户已存在");
        }
        UserDO userDO =
                UserDO.builder().email(email).name(name).password(passwordEncoder.encode(password)).department(department).build();
        return userMapper.insert(userDO) == 1;
    }

    public UserDO getCurrentMember(String token) {
        SecurityContext ctx = SecurityContextHolder.getContext();
        Authentication auth = ctx.getAuthentication();
        LoginUser loginUser = (LoginUser) auth.getPrincipal();
        return loginUser.getUser();
    }

    public String refreshToken(String token) {
        return jwtTokenUtil.refreshHeadToken(token);
    }

    public boolean updatePassword(String email, String password) {
        QueryWrapper<UserDO> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("email", email);
        List<UserDO> userDoS = userMapper.selectList(queryWrapper);
        if (CollectionUtils.isEmpty(userDoS)) {
            throw new ApiException("账户不存在");
        }
        UpdateWrapper<UserDO> updateWrapper = new UpdateWrapper<>();
        UserDO userDO = UserDO.builder().password(passwordEncoder.encode(password)).build();
        return userMapper.update(userDO, updateWrapper) == 1;

    }
}
