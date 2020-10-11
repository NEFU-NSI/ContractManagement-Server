package nsi.contractManagement.config.security;

import lombok.extern.slf4j.Slf4j;
import nsi.contractManagement.config.response.Result;
import nsi.contractManagement.config.response.ResultStatus;
import nsi.contractManagement.utils.ServletUtils;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @Author: Tao
 * @Time: 2020/10/11 10:49
 * @ProjectName: contract-management
 * @FileName: AnonymousAuthenticationEntryPoint.java
 * @IDE: IntelliJ IDEA
 */
@Slf4j
@Component
public class AnonymousAuthenticationEntryPoint implements AuthenticationEntryPoint {
    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response,
                         AuthenticationException e) throws IOException, ServletException {
        log.warn("用户需要登录，访问[{}]失败，AuthenticationException={}", request.getRequestURI(), e);
        ServletUtils.render(request, response, Result.failure(ResultStatus.USER_NEED_LOGIN,
                "用户未登录"));
    }
}
