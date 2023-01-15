package com.subh.springdemo.common;

public class AccessDeniedException extends RuntimeException {

	public AccessDeniedException(String arg0, Throwable arg1) {
		super(arg0, arg1);

	}

	public AccessDeniedException(Throwable arg0) {
		super(arg0);

	}

	public AccessDeniedException(String message) {
		super(message);
	}

}
