package com.example.nikolatest.cruddemovezba.exceptionhandler;

public class EmployeeException extends RuntimeException {

	private static final long serialVersionUID = -2425997016018457911L;
	

	private EmployeeException() {
		super();
		
	}
	public EmployeeException(String message, Throwable cause) {
		super(message, cause);
		// TODO Auto-generated constructor stub
	}

	public EmployeeException(String message) {
		super(message);
		// TODO Auto-generated constructor stub
	}

	public EmployeeException(Throwable cause) {
		super(cause);
		// TODO Auto-generated constructor stub
	}

	
}
