package cn.admin.config;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.UnsatisfiedServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import com.alibaba.fastjson.JSONObject;

import cn.commons.common.PublicResultJosn;
import feign.FeignException;

/**
 * 异常处理
 * 
 * @author 偶尔有点困
 * @date 2018年5月5日
 */
@RestControllerAdvice
public class ExceptionHandlerAdvice {

	private static final Logger log = LoggerFactory.getLogger(ExceptionHandlerAdvice.class);

	@ExceptionHandler({ FeignException.class })
	public Map<String, Object> feignException(FeignException exception, HttpServletResponse response) {
		int httpStatus = exception.status();
		if (httpStatus >= HttpStatus.INTERNAL_SERVER_ERROR.value()) {
			log.error("FeignClient调用异常", exception);
		}

		Map<String, Object> data = new HashMap<>();

		String msg = exception.getMessage();

		if (StringUtils.isNotBlank(msg)) {
			int index = msg.indexOf("\n");
			if (index > 0) {
				String string = msg.substring(index);
				if (StringUtils.isNotBlank(string)) {
					JSONObject json = JSONObject.parseObject(string.trim());
					data.putAll(json.getInnerMap());
				}
			}
		}
		if (data.isEmpty()) {
			data.put("message", msg);
		}
		data.put("code", httpStatus);
		response.setStatus(httpStatus);
		return data;
	}

	@ExceptionHandler({ IllegalArgumentException.class })
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	public PublicResultJosn badRequestException(IllegalArgumentException exception) {
		return new PublicResultJosn(HttpStatus.BAD_REQUEST.value(), exception.getMessage(), null);
	}

	@ExceptionHandler({ AccessDeniedException.class })
	@ResponseStatus(HttpStatus.FORBIDDEN)
	public PublicResultJosn badRequestException(AccessDeniedException exception) {
		return new PublicResultJosn(HttpStatus.FORBIDDEN.value(), exception.getMessage(), null);
	}

	@ExceptionHandler({ MissingServletRequestParameterException.class, HttpMessageNotReadableException.class,
			UnsatisfiedServletRequestParameterException.class, MethodArgumentTypeMismatchException.class })
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	public PublicResultJosn badRequestException(Exception exception) {
		return new PublicResultJosn(HttpStatus.BAD_REQUEST.value(), exception.getMessage(), null);
	}

	@ExceptionHandler(Throwable.class)
	@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
	public PublicResultJosn exception(Throwable throwable) {
		log.error("系统异常", throwable);
		return new PublicResultJosn(HttpStatus.INTERNAL_SERVER_ERROR.value(), throwable.getMessage(), null);

	}

}