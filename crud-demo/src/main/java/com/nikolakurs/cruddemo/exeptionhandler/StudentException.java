package com.nikolakurs.cruddemo.exeptionhandler;

public class StudentException  extends RuntimeException{

	private static final long serialVersionUID = 8126850819691581560L;
	//RUCNO SI GA OVVERIDE IZ RUNTIME
	public StudentException() {
		super();
	}
	//RUCNO SI GA OVVERIDE IZ RUNTIME
	public StudentException(String message, Throwable cause) {
		super(message, cause);

	}
	//RUCNO SI GA OVVERIDE IZ RUNTIME
	public StudentException(String message) {
		super(message);
	}
	//RUCNO SI GA OVVERIDE IZ RUNTIME
	public StudentException(Throwable cause) {
		super(cause);

	}




	
	

}
