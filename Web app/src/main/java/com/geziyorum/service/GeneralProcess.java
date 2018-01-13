package com.geziyorum.service;

import java.io.IOException;

public interface GeneralProcess {
	
	Object startService() throws IOException;
	Object validateService() throws IOException;
	Object processService() throws IOException;
}
