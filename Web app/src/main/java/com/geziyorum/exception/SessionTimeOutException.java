package com.geziyorum.exception;

import java.io.IOException;

public class SessionTimeOutException extends IOException{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public SessionTimeOutException(String msg)
	{
		super(msg);
	}
}
