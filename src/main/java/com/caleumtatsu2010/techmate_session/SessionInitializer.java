package com.caleumtatsu2010.techmate_session;

import javax.servlet.http.HttpSession;
import java.util.List;

public interface SessionInitializer {
	
	 void initialize(HttpSession session);
	
	 void setSessionAttribute(String attributeName, Object o);
	
	Object getSessionAttribute(String attributeName);
	
	List<Object> getAllSessionAttributes();
}
