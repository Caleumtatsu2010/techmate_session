package com.caleumtatsu2010.techmate_session.cassandra;

import com.caleumtatsu2010.techmate_session.SessionInstance;

import javax.servlet.http.HttpSession;
import java.util.List;

public abstract class SessionOperators implements SessionInstance {
	
	protected HttpSession session;
	
	@Override
	public void initialize(HttpSession session) {
		this.session = session;
	}
	
	abstract protected void setSessionAttribute(String attributeName, Object o);
	
	abstract protected Object getSessionAttribute(String attributeName);
	
	abstract protected List<Object> getAllSessionAttributes();
}
