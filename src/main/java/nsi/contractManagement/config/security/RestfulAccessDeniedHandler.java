package nsi.contractManagement.config.security;

import cn.hutool.json.JSONUtil;
import nsi.contractManagement.config.response.Result;
import nsi.contractManagement.config.response.ResultStatus;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 自定义返回结果：没有权限访问时
 *
 * @Author: Tao
 * @Time: 2020/10/11 15:44
 * @ProjectName: contract-management
 * @FileName: RestfulAccessDeniedHandler.java
 * @IDE: IntelliJ IDEA
 */
public class RestfulAccessDeniedHandler implements AccessDeniedHandler {
    @Override
    public void handle(HttpServletRequest request,
                       HttpServletResponse response,
                       AccessDeniedException e) throws IOException {
        response.setHeader("Access-Control-Allow-Origin", "*");
        response.setHeader("Cache-Control", "no-cache");
        response.setCharacterEncoding("UTF-8");
        response.setContentType("application/json");
        response.getWriter().println(JSONUtil.parse(Result.failure(ResultStatus.BAD_REQUEST,
                "权限不足")));
        response.getWriter().flush();
    }
}
