package com.zs.javaweb.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.zs.javaweb.domain.ShoppingCart;

public class BookStoreWebUtils {

	//类方法，如果session中没有ShoppingCart对象，则放到session中
	public static ShoppingCart getShoppingCart(HttpServletRequest request){
		HttpSession session = request.getSession();
		ShoppingCart sc = (ShoppingCart)session.getAttribute("ShoppingCart");
		if(sc == null){
			sc = new ShoppingCart();
			session.setAttribute("ShoppingCart", sc);
		}
		return sc;
	}
}
