package cn.web.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.security.authentication.AuthenticationCredentialsNotFoundException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.UnsatisfiedServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import cn.commons.common.PublicResultJosn;

/**
 * 异常处理
 * 
 * @author 偶尔有点困
 * @date 2018年5月5日
 */
@RestControllerAdvice
public class ExceptionHandlerAdvice {

    private static final Logger log = LoggerFactory.getLogger(ExceptionHandlerAdvice.class);

    @ExceptionHandler({ AuthenticationCredentialsNotFoundException.class })
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    public PublicResultJosn badRequestException(IllegalArgumentException exception) {
        return new PublicResultJosn(HttpStatus.UNAUTHORIZED.value(), exception.getMessage(), null);
    }

    @ExceptionHandler({ IllegalArgumentException.class, MissingServletRequestParameterException.class,
            HttpMessageNotReadableException.class, UnsatisfiedServletRequestParameterException.class,
            MethodArgumentTypeMismatchException.class })
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public PublicResultJosn badRequestException(Exception exception) {
        return new PublicResultJosn(HttpStatus.BAD_REQUEST.value(), exception.getMessage(), null);
    }

    @ExceptionHandler({ NullPointerException.class })
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public PublicResultJosn nullPointerException(NullPointerException exception) {
        return new PublicResultJosn(HttpStatus.NOT_FOUND.value(), HttpStatus.NOT_FOUND.getReasonPhrase(), null);
    }

    @ExceptionHandler(Throwable.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public PublicResultJosn exception(Throwable throwable) {
        log.error("系统异常", throwable);
        return new PublicResultJosn(HttpStatus.INTERNAL_SERVER_ERROR.value(), throwable.getMessage(), null);

    }

}