package com.geziyorum.exception;

import java.io.IOException;

public class WrongUserOrPasswordException extends IOException{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public WrongUserOrPasswordException(String msg){
		super(msg);
	}
}
