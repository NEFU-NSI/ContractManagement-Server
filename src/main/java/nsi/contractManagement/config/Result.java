package nsi.contractManagement.config;

import lombok.Data;

/**
 * @Author: Tao @Time: 2020/10/8 17:30 @ProjectName: contract-management @FileName:
 * Result.java @IDE: IntelliJ IDEA
 */
@Data
public class Result<T> {
    /**
     * 业务错误码
     */
    private Integer code;
    /**
     * 信息描述
     */
    private String message;
    /**
     * 返回参数
     */
    private T data;

    private Result(ResultStatus resultStatus, T data) {
        this.code = resultStatus.getCode();
        this.message = resultStatus.getMessage();
        this.data = data;
    }

    private Result(ResultStatus resultStatus, String message) {
        this.code = resultStatus.getCode();
        this.message = message;
    }

    /**
     * 业务成功返回业务代码和描述信息
     */
    public static Result<Void> success() {
        return new Result<>(ResultStatus.SUCCESS, null);
    }

    /**
     * 业务成功返回业务代码,描述和返回的参数
     */
    public static <T> Result<T> success(T data) {
        return new Result<>(ResultStatus.SUCCESS, data);
    }

    /**
     * 业务成功返回业务代码,描述和返回的参数
     */
    public static <T> Result<T> success(ResultStatus resultStatus, T data) {
        if (resultStatus == null) {
            return success(data);
        }
        return new Result<>(resultStatus, data);
    }

    /**
     * 业务异常返回业务代码和描述信息
     */
    public static <T> Result<T> failure() {
        return new Result<>(ResultStatus.INTERNAL_SERVER_ERROR, null);
    }

    /**
     * 业务异常返回业务代码,描述和返回的参数
     */
    public static <T> Result<T> failure(ResultStatus resultStatus) {
        return failure(resultStatus, null);
    }

    /**
     * 参数错误
     */
    public static <T> Result<T> failure(String message) {
        return new Result<>(ResultStatus.PARAMETER_ERROR, message);
    }

    /**
     * 业务异常返回业务代码,描述和返回的参数
     */
    public static <T> Result<T> failure(ResultStatus resultStatus, T data) {
        if (resultStatus == null) {
            return new Result<>(ResultStatus.INTERNAL_SERVER_ERROR, null);
        }
        return new Result<>(resultStatus, data);
    }
}
