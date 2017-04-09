package com.elsewedy.common;

public class RestResponse {

	private String successMessage;

	public RestResponse(String successMessage) {
		this.successMessage = successMessage;
	}

	public String getSuccessMessage() {
		return successMessage;
	}

	public void setSuccessMessage(String successMessage) {
		this.successMessage = successMessage;
	}

	@Override
	public String toString() {
		return "RestResponse [successMessage=" + successMessage + "]";
	}
	
	
}
