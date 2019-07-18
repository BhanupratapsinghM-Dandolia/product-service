package com.myretail.productapi.exception;

import org.springframework.http.HttpStatus;

import com.myretail.productapi.model.ErrorInfo;

import lombok.Getter;

@Getter
public class GenericException extends RuntimeException {

	private static final long serialVersionUID = -3716019064741505532L;
	final ErrorInfo errorInfo;
	final HttpStatus httpStatus;

	public GenericException(ErrorInfo errorInfo, HttpStatus httpStatus) {
		this.errorInfo = errorInfo;
		this.httpStatus = httpStatus;
	}
}
