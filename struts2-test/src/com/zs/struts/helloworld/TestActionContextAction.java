package com.zs.struts.helloworld;

import java.util.Map;

import org.apache.struts2.dispatcher.Parameter;
import org.apache.struts2.dispatcher.SessionMap;

import com.opensymphony.xwork2.ActionContext;

public class TestActionContextAction {
	
	public String execute(){
		//ActionContext是Action的上下文对象
		ActionContext actionContext = ActionContext.getContext();
		//1.获取application对应的Map,往其中添加一个属性
		Map<String, Object>applicationMap = actionContext.getApplication();
		applicationMap.put("applicationKey", "applicationValue");
		//2.session
		Map<String, Object>sessionMap = actionContext.getSession();
		sessionMap.put("sessionKey", "sessionValue");
		if (sessionMap instanceof SessionMap) {
			SessionMap sm = (SessionMap) sessionMap;
			sm.invalidate();
			System.out.println("session 失效了");
			
		}
		//3.request,ActionContext并没有提供getRequest方法，需要手工调用get()方法，传入request字符串来获取
		Map<String, Object> requestMap = (Map<String, Object>)actionContext.get("request");
		requestMap.put("requestKey", "requestValue");
		//4.获取参数对应的Map
		Map<String, Parameter> parameters = actionContext.getParameters();
		System.out.println(parameters.get("name"));
		return "success";
	}
}
