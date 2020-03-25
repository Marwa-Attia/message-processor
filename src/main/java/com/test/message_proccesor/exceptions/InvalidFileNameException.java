package com.test.message_proccesor.exceptions;

public class InvalidFileNameException extends Exception {
	/**
	* 
	*/
	private static final long serialVersionUID = -7349019755952226161L;

	public InvalidFileNameException(String errorMessage) {
		super(errorMessage);
	}
}
