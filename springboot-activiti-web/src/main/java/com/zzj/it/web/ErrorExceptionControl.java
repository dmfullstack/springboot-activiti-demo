package com.zzj.it.web;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.autoconfigure.web.ErrorController;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

/**
 * 异常拦截
 * 
 * @author zhouzj <a href="https://github.com/zhouzuojun">工程路径</a>
 *
 *
 */
@RestController
public class ErrorExceptionControl implements ErrorController {

	private final static String ERROR_PATH = "error";

	private final static Logger LOGGER = LoggerFactory.getLogger(ErrorExceptionControl.class);

	public String getErrorPath() {
		return ERROR_PATH;
	}

	public ResponseEntity<Object> error(HttpServletRequest request, Exception e) {
		HttpStatus httpStatus = getStatus(request);

		LOGGER.error("运行时异常:{}", e);
		Map<String, Object> map = new HashMap<String, Object>();

		map.put("status", httpStatus.value());
		map.put("msg", e);
		return new ResponseEntity<Object>(map, httpStatus);
	}

	private HttpStatus getStatus(HttpServletRequest httpServletRequest) {
		Integer code = (Integer) httpServletRequest.getAttribute("javax.servlet.error.status_code");
		if (code == null) {
			return HttpStatus.INTERNAL_SERVER_ERROR;
		}
		return HttpStatus.valueOf(code);
	}

}
