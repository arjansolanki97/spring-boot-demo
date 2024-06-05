package com.anjaneya.spring.boot.demo.entites.response;

import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;

@Getter
@Setter
public class ResponseContainerEntity<T> {

	private T body;

	private HttpStatus httpStatus;

	private boolean isError;

	private String message;

	public ResponseContainerEntity(T body) {
		this.body = body;
	}

	public ResponseContainerEntity(HttpStatus httpStatus) {
		this.httpStatus = httpStatus;
	}

	public ResponseContainerEntity(String message, T body) {
		this.message = message;
		this.body = body;
	}

	public ResponseContainerEntity(String message, T body, HttpStatus httpStatus) {
		this.message = message;
		this.body = body;
		this.httpStatus = httpStatus;
	}

	public ResponseContainerEntity(String message, HttpStatus httpStatus) {
		this.httpStatus = httpStatus;
		this.message = message;
	}

	public ResponseContainerEntity(String message, T body, boolean isError) {
		this.message = message;
		this.body = body;
		this.isError = isError;
	}

	public ResponseContainerEntity(String message, T body, HttpStatus httpStatus, boolean isError) {
		this.message = message;
		this.body = body;
		this.httpStatus = httpStatus;
		this.isError = isError;
	}

	public ResponseContainerEntity() {
		super();
	}

}