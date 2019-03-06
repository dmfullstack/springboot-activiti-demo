package com.zzj.it.exception;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.autoconfigure.web.ErrorController;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import com.zzj.it.bean.ApplicationResult;

/**
 * 异常拦截
 * 
 * @author zhouzj <a href="https://github.com/zhouzuojun">工程路径</a>
 *
 *
 */
@RestController
public class ErrorExceptionControl implements ErrorController {

	private static final String JAVAX_SERVLET_ERROR_STATUS_CODE = "javax.servlet.error.status_code";

	private final static String ERROR_PATH = "error";

	private final static Logger logger = LoggerFactory.getLogger(ErrorExceptionControl.class);

	public String getErrorPath() {
		return ERROR_PATH;
	}

	public ResponseEntity<Object> error(HttpServletRequest request, Exception e) {
		HttpStatus httpStatus=getStatus(request);
		ApplicationResult<Object> result=new ApplicationResult<Object>();
		result.setState(httpStatus.value());
		result.setMessage(e.getMessage());
		logger.error("执行异常{}",e);
		return new ResponseEntity<Object>(result,httpStatus);
	}

	private HttpStatus getStatus(HttpServletRequest httpServletRequest) {
		Integer code = (Integer) httpServletRequest.getAttribute(JAVAX_SERVLET_ERROR_STATUS_CODE);
		if (code == null) {
			return HttpStatus.INTERNAL_SERVER_ERROR;
		}
		return HttpStatus.valueOf(code);
	}

}
