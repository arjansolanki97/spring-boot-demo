package com.anjaneya.spring.boot.demo.exceptions;

import com.anjaneya.spring.boot.demo.entites.response.ApiResponseContainer;
import com.anjaneya.spring.boot.demo.entites.response.ResponseContainerEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.ArrayList;
import java.util.List;

@RestControllerAdvice
public class ExceptionsHandler {

	@ExceptionHandler(value = {CustomException.class})
	public ResponseEntity<ResponseContainerEntity<Object>> handleCustomException(CustomException customException) {
		ResponseContainerEntity<Object> responseContainerEntity = ApiResponseContainer.getResponse(customException.getMessage(), null, HttpStatus.BAD_REQUEST, Boolean.TRUE);
		return new ResponseEntity<>(responseContainerEntity, responseContainerEntity.getHttpStatus());
	}

	@ExceptionHandler(value = {ResourceNotFoundException.class})
	public ResponseEntity<ResponseContainerEntity<Object>> handleResourceNotFoundException(ResourceNotFoundException resourceNotFoundException) {
		ResponseContainerEntity<Object> responseContainerEntity = ApiResponseContainer.getResponse(resourceNotFoundException.getMessage(), null, HttpStatus.NOT_FOUND, Boolean.FALSE);
		return new ResponseEntity<>(responseContainerEntity, responseContainerEntity.getHttpStatus());
	}

	@ExceptionHandler(value = { MethodArgumentNotValidException.class })
	public ResponseEntity<ResponseContainerEntity<Object>> handleMethodArgumentNotValidException(MethodArgumentNotValidException methodArgumentNotValidException) {
		List<String> errors = new ArrayList<>();
		methodArgumentNotValidException.getBindingResult().getFieldErrors().forEach((FieldError error) -> {
			String fieldName = error.getField();
			String message = error.getDefaultMessage();
			errors.add(message);
		});
		ResponseContainerEntity<Object> responseContainerEntity = ApiResponseContainer.getResponse(String.join(",", errors), null, HttpStatus.BAD_REQUEST, Boolean.TRUE);
		return new ResponseEntity<>(responseContainerEntity, responseContainerEntity.getHttpStatus());
	}
}
