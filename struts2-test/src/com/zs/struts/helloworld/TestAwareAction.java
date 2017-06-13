package com.zs.struts.helloworld;

import java.util.Map;

import org.apache.struts2.interceptor.ApplicationAware;
import org.apache.struts2.interceptor.ParameterAware;
import org.apache.struts2.interceptor.RequestAware;
import org.apache.struts2.interceptor.SessionAware;

import freemarker.template.utility.Execute;

public class TestAwareAction implements ApplicationAware, SessionAware, 
	RequestAware, ParameterAware {
	
	public String execute(){
		application.put("applicationKey", "applicationValue");
		return "success";
	}

	@Override
	public void setParameters(Map<String, String[]> parameters) {
		
	}

	@Override
	public void setRequest(Map<String, Object> request) {
		
	}

	@Override
	public void setSession(Map<String, Object> session) {
		
	}
	private Map<String, Object> application;
	@Override
	public void setApplication(Map<String, Object> application) {
		this.application = application;
	}
	
	
}
