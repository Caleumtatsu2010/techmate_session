package com.caleumtatsu2010.techmate_session.cassandra.astra;

import com.caleumtatsu2010.techmate_session.cassandra.astra.model.AstraSession;
import com.caleumtatsu2010.techmate_session.http.operator.HttpSessionInitializerOperators;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.stream.Collectors;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class AstraHttpSessionOperators  {
	
	private HttpSessionInitializerOperators httpSessionInitializerOperators;
	
	public void getSessionData(HttpServletRequest request) {
		httpSessionInitializerOperators.initialize(request.getSession());
	}
	
	public void setAstraSessionAttribute(String attributeName, AstraSession astraSession) {
		httpSessionInitializerOperators.setSessionAttribute(attributeName, astraSession);
	}
	
	public AstraSession getAstraSessionAttribute(String attributeName){
		return (AstraSession) httpSessionInitializerOperators.getSessionAttribute(attributeName);
	}
	
	public List<AstraSession> getAllAstraSessionAttribute(String attributeName) {
		List<Object> objectList = httpSessionInitializerOperators.getAllSessionAttributes();
		return objectList.stream().map(element -> (AstraSession) element).collect(Collectors.toList());
	}
	
}
