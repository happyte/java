package com.zs.javaweb.filter;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;

import com.zs.javaweb.db.JDBCUtils;
import com.zs.javaweb.web.ConnectionContext;


@WebFilter("/*")
public class TransactionFilter implements Filter {


    public TransactionFilter() {
      
    }


	public void destroy() {
		
	}


	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		Connection connection = null;
		try {
			//1.获取连接
			connection = JDBCUtils.getConnection();
			//2.开启事务
			connection.setAutoCommit(false);
			//3.利用ThreadLocal把当前Connection和线程绑定
			ConnectionContext.getInstance().bind(connection);
			//4.把请求目标转给Servlet,这样来到Servlet的处理方法
			chain.doFilter(request, response);
			//5.提交事务
			connection.commit();
		} catch (Exception e) {
			//6.事务回滚
			try {
				connection.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		}finally {
			//7.解除绑定
			ConnectionContext.getInstance().remove();
			//8.关闭连接
			JDBCUtils.release(connection);
		}
	}

	
	public void init(FilterConfig fConfig) throws ServletException {
		
	}

}
