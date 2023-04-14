package org.zerock.myapp.exception;

public class DAOException extends Exception {
	private static final long serialVersionUID = 1L;
	
	public DAOException(String message) {
		super(message);
	}	//constructor 1
	
	public DAOException(Exception originalException) {
		super(originalException);
	}	//constructor 2
	

}	// end class
