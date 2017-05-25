package com.zs.javaweb;

import java.io.IOException;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.SimpleTagSupport;

import com.sun.xml.internal.bind.v2.runtime.Name;

public class ParentTag extends SimpleTagSupport {
	private String name = "www.baidu.com";
	
	public String getName() {
		return name;
	}
	
	@Override
	public void doTag() throws JspException, IOException {
		System.out.println("name:" + name);
		//把标签体显示到页面上,现在的标签体是子标签
		getJspBody().invoke(null);
	}

}
