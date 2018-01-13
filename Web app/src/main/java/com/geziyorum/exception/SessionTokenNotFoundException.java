package com.geziyorum.exception;

import java.io.IOException;

public class SessionTokenNotFoundException extends IOException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public SessionTokenNotFoundException(String msg){
		super(msg);
	}
}
