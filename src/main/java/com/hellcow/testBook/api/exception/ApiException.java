package com.hellcow.testBook.api.exception;

import com.fasterxml.jackson.annotation.JsonRootName;

import javax.ws.rs.core.Response;

// Rest API 연동 오류
@JsonRootName(value = "error")
public class ApiException extends Exception {

	private static final long serialVersionUID = 1L;
	private int httpStatus = Response.Status.OK.getStatusCode();
	private String res_code;
	private String res_msg;

	public ApiException(int httpStatus, String res_msg) {
		this.httpStatus = httpStatus;
		this.res_code = String.valueOf(httpStatus);
		this.res_msg = res_msg;
	}

	public ApiException(int httpStatus, String message, String res_code) {
		this.httpStatus = httpStatus;
		this.res_msg = message;
		this.res_code = res_code;
	}

	public ApiException(String message, String res_code) {
		this.res_msg = message;
		this.res_code = res_code;
		this.httpStatus = Integer.valueOf(this.res_code);
	}

	public ApiException(String message) {
		this.res_msg = message;
		this.httpStatus = Response.Status.INTERNAL_SERVER_ERROR.getStatusCode();
		this.res_code = String.valueOf(this.httpStatus);
	}

	public ApiException(Throwable cause) {
		super(cause);
	}

	public String getRes_code() {
		return this.res_code;
	}
	public String getRes_msg() {
		return this.res_msg;
	}
	public int getHttpStatus() {
		return this.httpStatus;
	}
}
