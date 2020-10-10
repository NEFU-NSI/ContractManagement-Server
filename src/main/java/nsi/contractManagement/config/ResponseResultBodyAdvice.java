package nsi.contractManagement.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.core.MethodParameter;
import org.springframework.core.annotation.AnnotatedElementUtils;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;
import org.springframework.web.util.WebUtils;

import java.lang.annotation.Annotation;
import java.util.Objects;

/**
 * @Author: Tao
 * @Time: 2020/10/8 17:36
 * @ProjectName: contract-management
 * @FileName: ResponseResultBodyAdvice.java
 * @IDE: IntelliJ IDEA
 */
@Slf4j
@RestControllerAdvice
public class ResponseResultBodyAdvice implements ResponseBodyAdvice<Object> {

    private static final Class<? extends Annotation> ANNOTATION_TYPE = ResponseResultBody.class;

    /**
     * 判断类或者方法是否使用了 @ResponseResultBody
     */
    @Override
    public boolean supports(
            MethodParameter returnType, Class<? extends HttpMessageConverter<?>> converterType) {
        return AnnotatedElementUtils.hasAnnotation(returnType.getContainingClass(), ANNOTATION_TYPE)
                || returnType.hasMethodAnnotation(ANNOTATION_TYPE);
    }

    /**
     * 当类或者方法使用了 @ResponseResultBody 就会调用这个方法
     */
    @Override
    public Object beforeBodyWrite(Object body, MethodParameter returnType,
                                  MediaType selectedContentType,
                                  Class<? extends HttpMessageConverter<?>> selectedConverterType,
                                  ServerHttpRequest request,
                                  ServerHttpResponse response) {
        /*
         * 防止重复包裹的问题出现
         * */
        if (body instanceof Result) {
            return body;
        }
        return Result.success(body);
    }

    @ExceptionHandler(Exception.class)
    public final ResponseEntity<Result<?>> exceptionHandler(Exception ex, WebRequest request) {
        log.error("ExceptionHandler: {}", ex.getMessage());
        HttpHeaders headers = new HttpHeaders();
        if (ex instanceof ResultException) {
            return this.handleResultException((ResultException) ex, headers, request);
        }
        if (ex instanceof MethodArgumentNotValidException) {
            return methodArgumentNotValidExceptionHandle((MethodArgumentNotValidException) ex,
                    headers, request);
        }
        return this.handleException(ex, headers, request);
    }

    /**
     * 对ResultException类返回返回结果的处理
     */
    protected ResponseEntity<Result<?>> handleResultException(ResultException ex,
                                                              HttpHeaders headers,
                                                              WebRequest request) {
        Result<?> body = Result.failure(ex.getResultStatus());
        HttpStatus status = ex.getResultStatus().getHttpStatus();
        return this.handleExceptionInternal(ex, body, headers, status, request);
    }

    /**
     * 异常类的统一处理
     */
    protected ResponseEntity<Result<?>> handleException(Exception ex, HttpHeaders headers,
                                                        WebRequest request) {
        Result<?> body = Result.failure();
        HttpStatus status = HttpStatus.INTERNAL_SERVER_ERROR;
        return this.handleExceptionInternal(ex, body, headers, status, request);
    }
    /*
    参数错误
     */

    protected ResponseEntity<Result<?>> methodArgumentNotValidExceptionHandle(MethodArgumentNotValidException ex,
                                                                              HttpHeaders headers,
                                                                              WebRequest request) {
        Result<?> body =
                Result.failure(Objects.requireNonNull(ex.getBindingResult().getFieldError()).getDefaultMessage());
        HttpStatus status = HttpStatus.BAD_REQUEST;
        return this.handleExceptionInternal(ex, body, headers, status, request);
    }


    protected ResponseEntity<Result<?>> handleExceptionInternal(
            Exception ex, Result<?> body, HttpHeaders headers, HttpStatus status,
            WebRequest request) {

        if (HttpStatus.INTERNAL_SERVER_ERROR.equals(status)) {
            request.setAttribute(WebUtils.ERROR_EXCEPTION_ATTRIBUTE, ex, WebRequest.SCOPE_REQUEST);
        }
        return new ResponseEntity<>(body, headers, status);
    }
}
