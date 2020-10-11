package nsi.contractManagement.config.response;

import org.springframework.web.bind.annotation.ResponseBody;

import java.lang.annotation.*;

/**
 * @Author: Tao
 * @Time: 2020/10/8 17:30
 * @ProjectName: contract-management
 * @FileName: Result.java
 * @IDE: IntelliJ IDEA
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE, ElementType.METHOD})
@Documented
@ResponseBody
public @interface ResponseResultBody {

}