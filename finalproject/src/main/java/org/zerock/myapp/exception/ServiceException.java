package org.zerock.myapp.exception;

public class ServiceException extends Exception {
	private static final long serialVersionUID = 1L;

	public ServiceException(String message) {
		super(message);
	}	// constructor1
	
	public ServiceException(Exception originalException) {
		super(originalException);
	}	// constructor2
	
}	// end class
