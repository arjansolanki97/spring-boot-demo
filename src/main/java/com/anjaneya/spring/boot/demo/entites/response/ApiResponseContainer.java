package com.anjaneya.spring.boot.demo.entites.response;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;

@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class ApiResponseContainer {

	public static <T> ResponseContainerEntity<T> getResponse(T body) {
		return new ResponseContainerEntity<>(body);
	}

	public static <T> ResponseContainerEntity<T> getResponse(HttpStatus httpStatus) {
		return new ResponseContainerEntity<>(httpStatus);
	}

	public static <T> ResponseContainerEntity<T> getResponse(String message, T body) {
		return new ResponseContainerEntity<>(message, body);
	}

	public static <T> ResponseContainerEntity<T> getResponse(String message, HttpStatus httpStatus) {
		return new ResponseContainerEntity<>(message, httpStatus);
	}

	public static <T> ResponseContainerEntity<T> getResponse(String message, T body, HttpStatus httpStatus) {
		return new ResponseContainerEntity<>(message, body, httpStatus);
	}

	public static <T> ResponseContainerEntity<T> getResponse(String message, T body, boolean isError) {
		return new ResponseContainerEntity<>(message, body, isError);
	}

	public static <T> ResponseContainerEntity<T> getResponse(String message, T body, HttpStatus httpStatus, boolean isError) {
		return new ResponseContainerEntity<>(message, body, httpStatus, isError);
	}

}