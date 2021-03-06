package com.zzj.it.exception;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.zzj.it.bean.ApplicationResult;

@RestControllerAdvice
public class ExceptionAdpter {

	private static final String JAVAX_SERVLET_ERROR_STATUS_CODE = "javax.servlet.error.status_code";
	private final static Logger logger=LoggerFactory.getLogger(ExceptionAdpter.class);
	
	
	private HttpStatus getStatus(HttpServletRequest request) {
		
		Integer code=(Integer)request.getAttribute(JAVAX_SERVLET_ERROR_STATUS_CODE);
		if(code==null) {
			return HttpStatus.INTERNAL_SERVER_ERROR;
		}
		return HttpStatus.valueOf(code);
	}
	
	@ExceptionHandler(MyRunException.class)
	@ResponseBody
	public ResponseEntity<Object> myRunException(HttpServletRequest request,MyRunException e){
		HttpStatus httpStatus=getStatus(request);
		ApplicationResult<Object> result=new ApplicationResult<Object>();
		result.setState(e.getCode());
		result.setMessage(e.getMessage());
		logger.error("执行异常{}",e);
		return new ResponseEntity<Object>(result,httpStatus);
	}
	
	@ExceptionHandler(Exception.class)
	@ResponseBody
	public ResponseEntity<Object> myRunException(HttpServletRequest request,Exception e){
		HttpStatus httpStatus=getStatus(request);
		ApplicationResult<Object> result=new ApplicationResult<Object>();
		result.setState(httpStatus.value());
		result.setMessage(e.getMessage());
		logger.error("执行异常{}",e);
		return new ResponseEntity<Object>(result,httpStatus);
	}
	
}
