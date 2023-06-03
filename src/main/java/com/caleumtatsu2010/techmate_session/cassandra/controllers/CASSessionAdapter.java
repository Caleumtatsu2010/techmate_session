package com.caleumtatsu2010.techmate_session.cassandra.controllers;

import com.caleumtatsu2010.techmate_session.WebServerSessionInitializer;
import com.caleumtatsu2010.techmate_session.cassandra.model.CASSession;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class CASSessionAdapter {
	
	private WebServerSessionInitializer webServerSessionInitializer;
	
	public void loadSessionData(HttpServletRequest request) {
		this.webServerSessionInitializer.initialize(request.getSession());
	}
	
	public void setCASSessionAttribute(String attributeName, CASSession casSession) {
		this.webServerSessionInitializer.setSessionAttribute(attributeName, casSession);
	}
	
	public CASSession getCASSessionAttribute(String attributeName){
		return (CASSession) this.webServerSessionInitializer.getSessionAttribute(attributeName);
	}
	
}
