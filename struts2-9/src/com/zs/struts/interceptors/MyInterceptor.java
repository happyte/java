package com.zs.struts.interceptors;

import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.AbstractInterceptor;

public class MyInterceptor extends AbstractInterceptor {

	private static final long serialVersionUID = 1L;

	@Override
	public String intercept(ActionInvocation arg0) throws Exception {
		System.out.println("before invocation.invoke.......");
		//如果不调用inoke方法，则后面的拦截器都不会调用，比如token表单重复验证的拦截器
		String result = arg0.invoke();
		System.out.println("after invocation.invoke.......");
		//刚才return result不行
		return "success";
	}

}
