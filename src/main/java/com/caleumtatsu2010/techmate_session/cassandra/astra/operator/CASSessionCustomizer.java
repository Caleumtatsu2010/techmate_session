package com.caleumtatsu2010.techmate_session.cassandra.astra.operator;

import com.caleumtatsu2010.techmate_session.cassandra.astra.model.AstraSession;
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
public class CASSessionCustomizer {
	
	private HttpSessionOperatorsInitializer httpSessionOperatorsInitializer;
	
	public void loadSessionData(HttpServletRequest request) {
		this.httpSessionOperatorsInitializer.initialize(request.getSession());
	}
	
	public void setAstraSessionAttribute(String attributeName, AstraSession astraSession) {
		this.httpSessionOperatorsInitializer.setSessionAttribute(attributeName, astraSession);
	}
	
	public AstraSession getAstraSessionAttribute(String attributeName){
		return (AstraSession) this.httpSessionOperatorsInitializer.getSessionAttribute(attributeName);
	}
	
	public List<AstraSession> getAllAstraSessionAttribute(String attributeName) {
		List<Object> objectList = this.httpSessionOperatorsInitializer.getAllSessionAttributes();
		return objectList.stream().map(element -> (AstraSession) element).collect(Collectors.toList());
	}
	
}
