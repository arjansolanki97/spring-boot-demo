package com.anjaneya.spring.boot.demo.exceptions;

public class ResourceNotFoundException extends RuntimeException {

	public ResourceNotFoundException(String message) {
		super(message);
	}

	public ResourceNotFoundException(String resource, Long id) {
		super(resource + " with id " + id + " not found");
	}

}
