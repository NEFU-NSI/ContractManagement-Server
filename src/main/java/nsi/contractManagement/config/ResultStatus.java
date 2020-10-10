package nsi.contractManagement.config;

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
    PARAMETER_ERROR(HttpStatus.BAD_REQUEST, 501, "参数错误");

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
