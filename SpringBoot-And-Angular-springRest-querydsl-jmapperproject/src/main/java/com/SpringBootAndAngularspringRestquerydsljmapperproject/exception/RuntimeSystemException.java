package com.elsewedy.exception;

public class RuntimeSystemException extends RuntimeException {

	private static final long serialVersionUID = 1L;
	private RestResponseError restResponseError;

	public RuntimeSystemException() {
	}

	public RuntimeSystemException(RestResponseError restResponseError) {
 		this.restResponseError = restResponseError;
	}

	public RestResponseError getRestResponseError() {
		return restResponseError;
	}

	public void setRestResponseError(RestResponseError restResponseError) {
		this.restResponseError = restResponseError;
	}

	@Override
	public String toString() {
		return "RuntimeSystemException [restResponseError=" + restResponseError + "]";
	}
	 
	 
}
