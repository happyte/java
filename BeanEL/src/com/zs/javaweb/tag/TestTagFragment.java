package com.zs.javaweb.tag;

import java.io.IOException;
import java.io.StringWriter;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.JspFragment;
import javax.servlet.jsp.tagext.SimpleTagSupport;

public class TestTagFragment extends SimpleTagSupport {
	
	@Override
	public void doTag() throws JspException, IOException {
		 System.out.println("doTag");
		 StringWriter sw = new StringWriter();
		 JspFragment jspFragment = getJspBody();
		 //invoke函数，invoke(Writer)把标签体内容从Writer中输出,若Writer为null
		 //等同于invoke(getJspContext().getOut()),即直接把标签体内容输出到页面上
		 jspFragment.invoke(sw);
		 String content = sw.toString();
		 content = content.toUpperCase();
		 getJspContext().getOut().print(content);
		 //jspFragment.invoke(getJspContext().getOut());
	}

}
