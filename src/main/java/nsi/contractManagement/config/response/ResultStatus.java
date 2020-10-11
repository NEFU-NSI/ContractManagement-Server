package nsi.contractManagement.config.response;

import lombok.Getter;
import lombok.ToString;
import org.springframework.http.HttpStatus;

/**
 * @author Tao
 */
@ToString
@Getter
public enum ResultStatus {

    /*
    成功
     */
    SUCCESS(HttpStatus.OK, 200, "OK"),
    /*
    失败
     */
    BAD_REQUEST(HttpStatus.BAD_REQUEST, 400, "Bad Request"),
    /*
    服务错误
     */
    INTERNAL_SERVER_ERROR(HttpStatus.INTERNAL_SERVER_ERROR, 500, "Internal Server Error"),
    /*
    参数错误
     */
    PARAMETER_ERROR(HttpStatus.BAD_REQUEST, 501, "参数错误"),
    /*
      密码错误
       */
    USER_PASSWORD_ERROR(HttpStatus.BAD_REQUEST, 503, "参数错误"),
    /*
     其它错误
      */
    USER_LOGIN_FAIL(HttpStatus.BAD_REQUEST, 504, "参数错误"),

    /**
     * 用户未登录
     */
    USER_NEED_LOGIN(HttpStatus.BAD_REQUEST, 505, "用户未登录"),
    /**
     * 用户登录超市
     */
    USER_LOGIN_TIMEOUT(HttpStatus.BAD_REQUEST, 506, "用户登录超时");
    /**
     * 返回的HTTP状态码, 符合http请求
     */
    private HttpStatus httpStatus;
    /**
     * 业务异常码
     */
    private Integer code;
    /**
     * 业务异常信息描述
     */
    private String message;

    ResultStatus(HttpStatus httpStatus, Integer code, String message) {
        this.httpStatus = httpStatus;
        this.code = code;
        this.message = message;
    }
}
