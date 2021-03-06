package com.osdnu.project.exceptions;

import javax.servlet.ServletException;

public class BadRequestException extends ServletException {
	private String errorMessage;
	private String errorCode = "0";

	public BadRequestException(String message) {
		super(message);
		this.errorMessage = message;
	}

	public BadRequestException(String errorCode, String message) {
		super(message);
		this.errorCode = errorCode;
	}

	public String getErrorMessage() {
		return errorMessage;
	}

	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}

	public String getErrorCode() {
		return errorCode;
	}

	public void setErrorCode(String errorCode) {
		this.errorCode = errorCode;
	}
}
