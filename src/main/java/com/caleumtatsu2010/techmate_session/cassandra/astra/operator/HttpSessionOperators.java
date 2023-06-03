package com.caleumtatsu2010.techmate_session.cassandra.astra.operator;

import com.caleumtatsu2010.techmate_session.HttpSessionInstance;

import javax.servlet.http.HttpSession;
import java.util.List;

public abstract class HttpSessionOperators implements HttpSessionInstance {
	
	protected HttpSession httpSession;
	
	@Override
	public void initialize(HttpSession session) {
		this.httpSession = session;
	}
	
	abstract protected void setSessionAttribute(String attributeName, Object o);
	
	abstract protected Object getSessionAttribute(String attributeName);
	
	abstract protected List<Object> getAllSessionAttributes();
}
