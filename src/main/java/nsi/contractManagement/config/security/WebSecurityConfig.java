package nsi.contractManagement.config.security;

import nsi.contractManagement.service.impl.CustomUserDetailsServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

/**
 * @Author: Tao
 * @Time: 2020/10/11 10:43
 * @ProjectName: contract-management
 * @FileName: WebSecurityConfig.java
 * @IDE: IntelliJ IDEA
 */

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
    /**
     * 登录成功的处理
     */
    @Autowired
    private LoginSuccessHandler loginSuccessHandler;
    /**
     * 登出成功的处理
     */
    @Autowired
    private LogoutSuccessHandler logoutSuccessHandler;
    /**
     * UserServiceImpl
     */
    @Autowired
    private CustomUserDetailsServiceImpl customUserDetailsServiceImpl;

    /**
     * 登陆失败
     */
    @Autowired
    private LoginFailureHandle loginFailureHandle;
    /**
     * 用户未登录
     */
    @Autowired
    private AnonymousAuthenticationEntryPoint anonymousAuthenticationEntryPoint;
    /**
     * 用户登录超时
     */
    @Autowired
    private InvalidSessionHandler invalidSessionHandler;

    private static final String[] WHITELIST = {
            "/v3/**",
            "/configuration/ui",
            "/swagger-resources/**",
            "/configuration/security",
            "/webjars/**",
            "/login", "/logout", "/error","/swagger-ui/**","/api/user/register"
    };


    /**
     * 配置认证方式等
     *
     * @param auth
     * @throws Exception
     */
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(customUserDetailsServiceImpl).passwordEncoder(passwordEncoder());
    }

    /**
     * http相关的配置，包括登入登出、异常处理、会话管理等
     *
     * @param http
     * @throws Exception
     */
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.cors().and().csrf().disable();
        http.authorizeRequests()
                // 放行接口
                .antMatchers(WHITELIST).permitAll()
                // 除上面外的所有请求全部需要鉴权认证
                .anyRequest().authenticated()
                // 异常处理(权限拒绝、登录失效等)
                .and().exceptionHandling()
                //匿名用户访问无权限资源时的异常处理
                .authenticationEntryPoint(anonymousAuthenticationEntryPoint)
                // 登入
                .and().formLogin().permitAll()//允许所有用户
                //登录成功处理逻辑
                .successHandler(loginSuccessHandler)
                //登录失败处理逻辑
                .failureHandler(loginFailureHandle)
                // 登出
                //允许所有用户
                .and().logout().permitAll()
                //登出成功处理逻辑
                .logoutSuccessHandler(logoutSuccessHandler)
                .deleteCookies(RestHttpSessionIdResolver.AUTH_TOKEN)
                // 会话管理
                // 超时处理
                .and().sessionManagement().invalidSessionStrategy(invalidSessionHandler)
                //同一账号同时登录最大用户数
                .maximumSessions(1)
        ;

    }

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

}