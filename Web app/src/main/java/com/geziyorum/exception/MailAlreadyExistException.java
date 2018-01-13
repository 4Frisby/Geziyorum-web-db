package com.geziyorum.exception;

import java.io.IOException;

public class MailAlreadyExistException extends IOException {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public MailAlreadyExistException(String msg){
		super(msg);
	}
}
