package nsi.contractManagement.config.security;

import lombok.extern.slf4j.Slf4j;
import nsi.contractManagement.config.response.Result;
import nsi.contractManagement.utils.ServletUtils;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @Author: Tao
 * @Time: 2020/10/11 10:41
 * @ProjectName: contract-management
 * @FileName: LogoutSuccessHandler.java
 * @IDE: IntelliJ IDEA
 */

@Slf4j
@Component
public class LogoutSuccessHandler implements org.springframework.security.web.authentication.logout.LogoutSuccessHandler {

    @Override
    public void onLogoutSuccess(HttpServletRequest request, HttpServletResponse response,
                                Authentication authentication) throws IOException,
           {

        // TODO 登出成功 记录登出日志
        ServletUtils.render(request, response, Result.success());
    }
}