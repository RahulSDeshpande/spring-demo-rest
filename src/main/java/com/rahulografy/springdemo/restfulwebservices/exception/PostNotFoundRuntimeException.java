package com.rahulografy.springdemo.restfulwebservices.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class PostNotFoundRuntimeException extends RuntimeException {

	public PostNotFoundRuntimeException(String message) {
		super(message);
	}
}