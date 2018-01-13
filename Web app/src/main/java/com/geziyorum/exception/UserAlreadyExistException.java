package com.geziyorum.exception;

import java.io.IOException;

public class UserAlreadyExistException extends IOException{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public UserAlreadyExistException(String msg){
		super(msg);
	}
}
