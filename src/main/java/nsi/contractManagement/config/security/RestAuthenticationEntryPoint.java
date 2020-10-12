package nsi.contractManagement.config.security;

import cn.hutool.json.JSONUtil;
import nsi.contractManagement.config.response.Result;
import nsi.contractManagement.config.response.ResultStatus;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 自定义返回结果：未登录或登录过期
 *
 * @Author: Tao
 * @Time: 2020/10/11 15:38
 * @ProjectName: contract-management
 * @FileName: RestAuthenticationEntryPoint.java
 * @IDE: IntelliJ IDEA
 */
public class RestAuthenticationEntryPoint implements AuthenticationEntryPoint {
    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response,
                         AuthenticationException authException) throws IOException{
        response.setHeader("Access-Control-Allow-Origin", "*");
        response.setHeader("Cache-Control", "no-cache");
        response.setCharacterEncoding("UTF-8");
        response.setContentType("application/json");
        response.getWriter().println(JSONUtil.parse(Result.failure(ResultStatus.USER_NEED_LOGIN,
                authException.getMessage())));
        response.getWriter().flush();
    }
}
