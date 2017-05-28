package com.zs.javaweb;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet("/authorityServlet")
public class AuthorityServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		doPost(request, response);
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		String methodName = request.getParameter("method");
		//利用反射创建对应的方法，比如method = getAuthority方法,则调用getAuthority方法
		try {
			//不能用request.getClass()方法代替
			Method method = getClass().getMethod(methodName, HttpServletRequest.class, HttpServletResponse.class);
			method.invoke(this, request, response);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	private UserDao userDao = new UserDao();
	private List<Authority> originUrls = new ArrayList<>();
	
	public void getAuthorities(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException{
		//把根据表单获取的用户加入到request属性中
		String username = request.getParameter("username");
		User user = userDao.getUser(username);
		List<Authority> authorities = userDao.getAuthorities();
		request.setAttribute("user", user);
		request.setAttribute("authorities", authorities);
		//转发到authority-manager.jsp
		request.getRequestDispatcher("authority-manager.jsp").forward(request, response);
	}
	
	//更新用户权限
	public void updateAuthorities(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException{
	    List<Authority> urls = new ArrayList<>();
		//字符串数组
		String[] authorities = request.getParameterValues("authority");
		//获取的用户名
		String username = request.getParameter("username");
		//获取全部属性
		originUrls = userDao.getAuthorities();
		if(authorities != null){
			for(Authority originUrl:originUrls){
				for(String authority:authorities){
					//两者获取的url相同
					if(authority.equals(originUrl.getUrl())){
						urls.add(originUrl);
					}
				}
			}
		}
		userDao.update(username, urls);
		//重定向到authority-manager.jsp
		response.sendRedirect(request.getContextPath()+"/authority-manager.jsp");
	}

}
