package com.subh.springdemo.common;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.github.fge.jsonpatch.JsonPatchException;
import com.subh.springdemo.rest.EmployeeErrorResponse;
import com.subh.springdemo.rest.EmployeeNotFoundException;

@ControllerAdvice
public class GlobalExceptionHandler {


	@ExceptionHandler
	public ResponseEntity handleBadRequestException(BadRequestException e) {

		APIResponse apiResponse = new APIResponse();
		apiResponse.setStatus(HttpStatus.BAD_REQUEST.value());
		apiResponse.setError(e.getErrors());

		return ResponseEntity.status(400).body(apiResponse);
	}

	@ExceptionHandler
	public ResponseEntity handleAccessDeniedException(AccessDeniedException e) {

		APIResponse apiResponse = new APIResponse();
		apiResponse.setError("Invalid token");
		apiResponse.setStatus(HttpStatus.UNAUTHORIZED.value());

		return ResponseEntity.status(apiResponse.getStatus()).body(apiResponse);
	}
	
	@ExceptionHandler
	public ResponseEntity<EmployeeErrorResponse> handleMyException(EmployeeNotFoundException exc) {

		EmployeeErrorResponse error = new EmployeeErrorResponse();

		error.setStatus(HttpStatus.NOT_FOUND.value());
		error.setMessage(exc.getMessage());
		error.setTimeStamp(System.currentTimeMillis());

		// return ResponseEntity

		return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
	}
	
	@ExceptionHandler
	public ResponseEntity<EmployeeErrorResponse> handleMyException(JsonPatchException exc) {


		EmployeeErrorResponse error = new EmployeeErrorResponse();

		error.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());
		error.setMessage(exc.getMessage());
		error.setTimeStamp(System.currentTimeMillis());

		// return ResponseEntity

		return new ResponseEntity<>(error, HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	@ExceptionHandler
	public ResponseEntity<EmployeeErrorResponse> handleMyException(JsonProcessingException exc) {

		EmployeeErrorResponse error = new EmployeeErrorResponse();

		error.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());
		error.setMessage(exc.getMessage());
		error.setTimeStamp(System.currentTimeMillis());

		// return ResponseEntity

		return new ResponseEntity<>(error, HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	@ExceptionHandler
	public ResponseEntity handleException(Exception e) {

		APIResponse apiResponse = new APIResponse();
		apiResponse.setError("Oops..Something went wrong!");
		apiResponse.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());

		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR.value()).body(apiResponse);
	}

}
