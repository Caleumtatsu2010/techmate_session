package com.caleumtatsu2010.techmate_session.cassandra.astra.operator;

import com.caleumtatsu2010.techmate_session.cassandra.SessionOperators;
import com.caleumtatsu2010.utility.common.StringValidator;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;

public class HttpSessionOperatorsInitializer extends SessionOperators {
	
	@Override
	public void initialize(HttpSession session) {
		super.initialize(session);
	}
	
	@Override
	public void setSessionAttribute(String attributeName, Object castObject) {
		super.session.setAttribute(StringValidator.NulltoBlank(attributeName), castObject);
	}
	
	@Override
	public Object getSessionAttribute(String attributeName) {
		return super.session.getAttribute(StringValidator.NulltoBlank(attributeName));
	}
	
	@Override
	public List<Object> getAllSessionAttributes() {
		List<Object> list = new ArrayList<>();
		Enumeration keys = super.session.getAttributeNames();
		while (keys.hasMoreElements()){
			String key = (String)keys.nextElement();
			list.add(session.getValue(key));
		}
		return list;
	}
}
