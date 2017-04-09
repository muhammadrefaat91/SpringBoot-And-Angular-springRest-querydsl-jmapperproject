package com.elsewedy.exception;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

 
@ControllerAdvice
public class RestResponseEntityExceptionHandler extends ResponseEntityExceptionHandler{

	@ExceptionHandler(RestResponseException.class)
	@ResponseBody
	public ResponseEntity<RestResponseError> handleRestResponseEntityExceptionHandler(RestResponseException restResponseError) {
		return new ResponseEntity<RestResponseError>(restResponseError.getRestResponseError(), restResponseError.getStatus());
	}
}
