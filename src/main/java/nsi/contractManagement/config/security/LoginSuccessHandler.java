package nsi.contractManagement.config.security;

import lombok.extern.slf4j.Slf4j;
import nsi.contractManagement.config.response.Result;
import nsi.contractManagement.utils.ServletUtils;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @Author: Tao
 * @Time: 2020/10/11 10:40
 * @ProjectName: contract-management
 * @FileName: LoginSuccessHandler.java
 * @IDE: IntelliJ IDEA
 */

@Slf4j
@Component
public class LoginSuccessHandler implements AuthenticationSuccessHandler {

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
                                        Authentication authentication) throws IOException,
            ServletException {

        // TODO 登录成功 记录日志
        ServletUtils.render(request, response, Result.success(authentication));
    }
}