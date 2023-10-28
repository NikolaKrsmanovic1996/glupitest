package com.nikolakurs.cruddemo.global.exception.util;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.nikolakurs.cruddemo.exeptionhandler.StudentErrorResponse;
import com.nikolakurs.cruddemo.exeptionhandler.StudentException;

@RestControllerAdvice
public class GlobalExcpetionHandler {
	
	@ExceptionHandler
	public ResponseEntity<StudentErrorResponse> handeExpetion(StudentException exeption){
	    
	    StudentErrorResponse error = new StudentErrorResponse();
	    error.setMessage(exeption.getMessage());
	    error.setStatus(HttpStatus.NOT_FOUND.value());
	    error.setTimeStamp(System.currentTimeMillis());
	    
	    
	    return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
		
		
	}
	
	@ExceptionHandler
	public ResponseEntity<StudentErrorResponse> handleAllExeption(Exception allException){
		StudentErrorResponse error = new StudentErrorResponse();
	    error.setMessage(allException.getMessage());
	    error.setStatus(HttpStatus.BAD_REQUEST.value());
	    error.setTimeStamp(System.currentTimeMillis());
	    
	    
	    return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
	}

}
