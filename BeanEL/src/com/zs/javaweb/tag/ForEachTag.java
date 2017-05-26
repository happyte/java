package com.zs.javaweb.tag;

import java.io.IOException;
import java.util.Collection;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.PageContext;
import javax.servlet.jsp.tagext.JspFragment;
import javax.servlet.jsp.tagext.SimpleTagSupport;

public class ForEachTag extends SimpleTagSupport {
	
	private Collection<?>items;
	private String var;
	
	public void setItems(Collection<?> items) {
		this.items = items;
	}
	
	public void setVar(String var) {
		this.var = var;
	}
	
	//items,var传递过来的都是属性值，例如items是Customer的集合,var是cust
	@Override
	public void doTag() throws JspException, IOException {
		//items是Customer的集合
		System.out.println("doTag");
		System.out.println("items:"+items);
		System.out.print("var:"+var);
		if (items != null) {
			for(Object obj:items){
				//JspPageContext是子类，PageContext是父类
				PageContext pageContext = (PageContext)getJspContext();
				pageContext.setAttribute(var, obj);
//				getJspContext().setAttribute(var, obj);
				getJspBody().invoke(null);
			}
		}
	}
}
