package com.zs.javaweb.tag;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.jsp.JspContext;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.PageContext;
import javax.servlet.jsp.tagext.JspFragment;
import javax.servlet.jsp.tagext.JspTag;
import javax.servlet.jsp.tagext.SimpleTag;

public class HelloSimpleTag implements SimpleTag {
	
	private String value;
	private String count;
	
	public void setValue(String value) {
		this.value = value;
		System.out.println("value:"+value);
	}
	
	public void setCount(String count) {
		this.count = count;
		System.out.println("count:"+count);
	}

	@Override
	public void doTag() throws JspException, IOException {
		System.out.println("doTag");
		HttpServletRequest request = (HttpServletRequest)this.pageContext.getRequest();
		//输出到JSP页面上
		pageContext.getOut().print("Hello"+request.getParameter("name"));
		JspWriter jspWriter = pageContext.getOut();
		
		int c = Integer.parseInt(this.count);
		for(int i = 0; i < c ; i++){
			jspWriter.print(i+1+":"+value);
			jspWriter.print("<br>");
		}
	}

	@Override
	public JspTag getParent() {
		System.out.println("getParent");
		return null;
	}

	@Override
	public void setJspBody(JspFragment arg0) {
		System.out.println("setJspBody");
	}

	private PageContext pageContext;
	//arg0是PageContext类型
	@Override
	public void setJspContext(JspContext arg0) {
		//System.out.println(arg0 instanceof PageContext);
		System.out.println("setJspContext");
		this.pageContext = (PageContext)arg0;
	}

	@Override
	public void setParent(JspTag arg0) {
		System.out.println("setParent");
	}

}
