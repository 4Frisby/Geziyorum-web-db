package com.geziyorum.exception;

import java.io.IOException;

public class ProfilePhotoNotFoundException extends IOException {
	public ProfilePhotoNotFoundException(String msg){
		super(msg);
	}
}
