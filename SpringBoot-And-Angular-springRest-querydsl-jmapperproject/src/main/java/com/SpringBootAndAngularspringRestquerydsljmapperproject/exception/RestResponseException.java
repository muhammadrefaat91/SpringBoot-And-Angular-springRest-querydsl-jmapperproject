package com.elsewedy.exception;

import org.springframework.http.HttpStatus;


public class RestResponseException extends RuntimeException {

	private static final long serialVersionUID = 1L;
	private HttpStatus status;
	private RestResponseError restResponseError;

	public RestResponseException() {
	}

	public RestResponseException(HttpStatus status, RestResponseError restResponseError) {
		this.status = status;
		this.restResponseError = restResponseError;
	}

	public HttpStatus getStatus() {
		return status;
	}

	public void setStatus(HttpStatus status) {
		this.status = status;
	}

	public RestResponseError getRestResponseError() {
		return restResponseError;
	}

	public void setRestResponseError(RestResponseError restResponseError) {
		this.restResponseError = restResponseError;
	}

	@Override
	public String toString() {
		return "RestResponseException [status=" + status + ", restResponseError=" + restResponseError + "]";
	}
	 
	 
}
