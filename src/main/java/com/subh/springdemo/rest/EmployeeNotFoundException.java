package com.subh.springdemo.rest;

public class EmployeeNotFoundException extends RuntimeException {

	public EmployeeNotFoundException(String arg0, Throwable arg1) {
		super(arg0, arg1);

	}

	public EmployeeNotFoundException(String arg0) {
		super(arg0);

	}

	public EmployeeNotFoundException(Throwable arg0) {
		super(arg0);

	}

}
