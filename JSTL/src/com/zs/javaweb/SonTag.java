package com.zs.javaweb;

import java.io.IOException;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.JspTag;
import javax.servlet.jsp.tagext.SimpleTagSupport;

public class SonTag extends SimpleTagSupport {
	
	@Override
	public void doTag() throws JspException, IOException {
		 //获得父标签
		 JspTag parent = (JspTag)getParent();
		 //强转成父标签类
		 ParentTag parentTag = (ParentTag)parent;
		 String name = parentTag.getName();
		 //输出到JSP页面上,因为内容是从父标签获得的，而不是子标签的标签体，因此不能调用invoke方法
		 getJspContext().getOut().print("name:"+name);
	}
}
