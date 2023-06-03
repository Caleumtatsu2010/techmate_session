package com.caleumtatsu2010.techmate_session;

import com.caleumtatsu2010.utility.common.StringValidator;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;

public class WebServerSessionInitializer implements SessionInitializer {
	
	private HttpSession session;
	
	@Override
	public void initialize(HttpSession session) {
		this.session = session;
	}
	
	@Override
	public void setSessionAttribute(String attributeName, Object castObject) {
		this.session.setAttribute(StringValidator.NulltoBlank(attributeName), castObject);
	}
	
	@Override
	public Object getSessionAttribute(String attributeName) {
		return this.session.getAttribute(StringValidator.NulltoBlank(attributeName));
	}
	
	@Override
	public List<Object> getAllSessionAttributes() {
		List<Object> list = new ArrayList<>();
		Enumeration keys = this.session.getAttributeNames();
		while (keys.hasMoreElements()){
			String key = (String)keys.nextElement();
			list.add(session.getValue(key));
		}
		return list;
	}
}
