package nsi.contractManagement.config.security;

import lombok.extern.slf4j.Slf4j;
import nsi.contractManagement.config.response.Result;
import nsi.contractManagement.config.response.ResultStatus;
import nsi.contractManagement.utils.ServletUtils;
import org.springframework.security.web.session.InvalidSessionStrategy;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @Author: Tao
 * @Time: 2020/10/11 10:54
 * @ProjectName: contract-management
 * @FileName: InvalidSessionHandler.java
 * @IDE: IntelliJ IDEA
 */

@Slf4j
@Component
public class InvalidSessionHandler implements InvalidSessionStrategy {

    @Override
    public void onInvalidSessionDetected(HttpServletRequest request, HttpServletResponse response) throws IOException {
        log.info("用户登录超时，访问[{}]失败", request.getRequestURI());

        ServletUtils.render(request, response, Result.failure(ResultStatus.USER_LOGIN_TIMEOUT,
                "登陆超时"));
    }
}
