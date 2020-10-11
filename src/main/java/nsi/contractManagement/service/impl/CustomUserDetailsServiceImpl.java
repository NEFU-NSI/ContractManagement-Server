package nsi.contractManagement.service.impl;

import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import lombok.extern.slf4j.Slf4j;
import nsi.contractManagement.DO.UserDO;
import nsi.contractManagement.DTO.LoginUser;
import nsi.contractManagement.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.web.authentication.WebAuthenticationDetails;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

/**
 * @Author: Tao
 * @Time: 2020/10/11 11:01
 * @ProjectName: contract-management
 * @FileName: CustomUserDetailsService.java
 * @IDE: IntelliJ IDEA
 */
@Slf4j
@Service
public class CustomUserDetailsServiceImpl  implements UserDetailsService {
    @Autowired
    private UserMapper userMapper;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        if (StrUtil.isBlank(username)) {
            log.info("登录用户：{} 不存在", username);
            throw new UsernameNotFoundException("登录用户：" + username + " 不存在");
        }

        // 查出密码
        UserDO userDO = userMapper.selectOne(new QueryWrapper<UserDO>().eq("email", username));
        if (ObjectUtil.isNull(userDO) || StrUtil.isBlank(userDO.getName())) {
            log.info("登录用户：{} 不存在", username);
            throw new UsernameNotFoundException("登录用户：" + username + " 不存在");
        }
        return new LoginUser(userDO, "", LocalDateTime.now());
    }
}
