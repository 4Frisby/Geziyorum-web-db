package com.geziyorum.exception;

import java.io.IOException;

public class FailedLoginException extends IOException {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public FailedLoginException(String message){
		super(message);
	}
	
}
