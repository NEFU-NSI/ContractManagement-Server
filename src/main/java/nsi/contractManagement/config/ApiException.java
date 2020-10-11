package nsi.contractManagement.config;

/**
 * @Author: Tao
 * @Time: 2020/10/10 19:37
 * @ProjectName: contract-management
 * @FileName: ApiException.java
 * @IDE: IntelliJ IDEA
 */
public class ApiException extends RuntimeException {
    public ApiException(String message) {
        super(message);
    }
}
