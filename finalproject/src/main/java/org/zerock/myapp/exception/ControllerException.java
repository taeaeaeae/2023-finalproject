package org.zerock.myapp.exception;


public class ControllerException extends Exception {
	private static final long serialVersionUID = 1L;
	
	
	public ControllerException(String message) {
		super(message);
	} // Constructor#1
	
	public ControllerException(Exception originalException) {
		super(originalException);
	} // Constructor#2

} // end class
