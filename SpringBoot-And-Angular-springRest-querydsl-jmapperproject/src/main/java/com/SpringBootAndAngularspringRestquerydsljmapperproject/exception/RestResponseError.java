package com.elsewedy.exception;

public class RestResponseError {

	private String message;
	private ErrorCode errorCode;

	public enum ErrorCode {
		NOT_UNIQUE, NULL, UNEXPECTED_ERROR, NOT_FOUND
	};
	
	public RestResponseError() {
	}

	public RestResponseError(String message, ErrorCode errorCode) {
		this.message = message;
		this.errorCode = errorCode;
	}
	
	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public ErrorCode getErrorCode() {
		return errorCode;
	}

	public void setErrorCode(ErrorCode errorCode) {
		this.errorCode = errorCode;
	}

	@Override
	public String toString() {
		return "ResponseRestError [message=" + message + ", errorCode=" + errorCode + "]";
	};

}
