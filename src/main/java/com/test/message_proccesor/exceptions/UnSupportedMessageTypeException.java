package com.test.message_proccesor.exceptions;

public class UnSupportedMessageTypeException extends Exception {
	/**
	 * 
	 */
	private static final long serialVersionUID = -5586346836931530343L;

	public UnSupportedMessageTypeException(String errorMessage) {
		super(errorMessage);
	}
}
