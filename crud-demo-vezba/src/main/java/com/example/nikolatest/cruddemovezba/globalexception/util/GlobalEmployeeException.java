package com.example.nikolatest.cruddemovezba.globalexception.util;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.example.nikolatest.cruddemovezba.exceptionhandler.EmployeeException;
import com.example.nikolatest.cruddemovezba.exceptionhandler.EmployeeExcpetionErrorMessage;

@RestControllerAdvice
public class GlobalEmployeeException {
	
	
	@ExceptionHandler
	private ResponseEntity<EmployeeExcpetionErrorMessage> badRequestError(EmployeeException exception){
		EmployeeExcpetionErrorMessage error = new EmployeeExcpetionErrorMessage();
		error.setMessage("Bad Data !");
		error.setStatus(HttpStatus.BAD_REQUEST.value());
		error.setTimeStamp(System.currentTimeMillis());
		
		return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
	}

	

	@ExceptionHandler
	private ResponseEntity<EmployeeExcpetionErrorMessage> notFound(EmployeeException exception){
		EmployeeExcpetionErrorMessage error = new EmployeeExcpetionErrorMessage();
		error.setMessage("Object cannot be founded");
		error.setStatus(HttpStatus.NOT_FOUND.value());
		error.setTimeStamp(System.currentTimeMillis());
		
		return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
	}
}
