package nsi.contractManagement.config.security;

import lombok.extern.slf4j.Slf4j;
import nsi.contractManagement.DO.ContractDO;
import nsi.contractManagement.DO.UserDO;
import nsi.contractManagement.config.response.Result;
import nsi.contractManagement.config.response.ResultStatus;
import nsi.contractManagement.utils.ServletUtils;
import org.springframework.security.authentication.*;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Enumeration;

/**
 * @Author: Tao
 * @Time: 2020/10/11 10:16
 * @ProjectName: contract-management
 * @FileName: LoginFailureHandle.java
 * @IDE: IntelliJ IDEA
 */
@Slf4j
@Component
public class LoginFailureHandle implements AuthenticationFailureHandler {
    @Override
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response,
                                        AuthenticationException e) throws IOException,
            ServletException {
        Result<UserDO> result = null;
        Enumeration<String> attributeNames = request.getSession().getAttributeNames();
        if (e instanceof BadCredentialsException) {
            // 密码错误
            log.info("[登录失败] - 用户[{}]密码错误", "username");
            result = Result.failure(ResultStatus.USER_PASSWORD_ERROR, "密码错误");
        } else if (e instanceof InternalAuthenticationServiceException) {
            // 内部错误
            log.error(String.format("[登录失败] - [%s]内部错误", "username"), e);
            result = Result.failure(ResultStatus.USER_LOGIN_FAIL, "内部错误");
        }
        // TODO 登录失败 记录日志
        ServletUtils.render(request, response, result);
    }
}