package com.as.blog.exceptions;

public class ApiException extends RuntimeException {

	public ApiException(String msg) {
		super(msg);
	}

	public ApiException() {
		super();
	}

}
