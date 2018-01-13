package com.geziyorum.exception;

import java.io.IOException;

public class WrongPasswordException extends IOException{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public WrongPasswordException(String msg){
		super(msg);
	}
}
