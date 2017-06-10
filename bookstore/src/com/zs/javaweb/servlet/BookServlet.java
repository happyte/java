package com.zs.javaweb.servlet;

import java.io.IOException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.google.gson.Gson;
import com.zs.javaweb.domain.Account;
import com.zs.javaweb.domain.Book;
import com.zs.javaweb.domain.CriteriaBook;
import com.zs.javaweb.domain.ShoppingCart;
import com.zs.javaweb.domain.ShoppingCartItem;
import com.zs.javaweb.domain.User;
import com.zs.javaweb.service.AccountService;
import com.zs.javaweb.service.BookService;
import com.zs.javaweb.service.UserService;
import com.zs.javaweb.web.BookStoreWebUtils;
import com.zs.javaweb.web.Page;


@WebServlet("/bookServlet")
public class BookServlet extends HttpServlet {
	
	private static final long serialVersionUID = 1L;

	private BookService bookService = new BookService();
	
	private UserService userService = new UserService();
	
	private AccountService accountService = new AccountService();

	protected void doGet(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		String methodName = request.getParameter("method");	
		try {
			Method method = getClass().getMethod(methodName, HttpServletRequest.class, HttpServletResponse.class);
			method.invoke(this, request, response);
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}
	
	//校验信息
	public void cash(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException{
		String username = request.getParameter("username");
		String accountId = request.getParameter("accountId");
		StringBuffer errors = new StringBuffer();
		//第一步：校验表单输入是否为空
		errors = validateFormField(username, accountId);
		//没有报错
		if(errors.toString().equals("")){
			//第二步: 校验用户名和accountId是否对应
			errors = validateUser(username, accountId);
			//第三步: 校验库存是否足够
			if(errors.toString().equals("")){
				errors = validateStoreNumber(request);
				if(errors.toString().equals("")){
					errors = validateBalance(request, accountId);
				}
			}
			//第四步: 校验余额是否足够
		}
		//校验出错
		if(!errors.toString().equals("")){
			request.setAttribute("errors", errors);
			request.getRequestDispatcher("/cash.jsp").forward(request, response);
			return;
		}
	}
	
	//校验余额
	private StringBuffer validateBalance(HttpServletRequest request, String accountId) {
		StringBuffer errors = new StringBuffer("");
		ShoppingCart sc = BookStoreWebUtils.getShoppingCart(request);
		System.out.println("accountId:"+Integer.parseInt(accountId));
		Account account = accountService.getAccount(Integer.parseInt(accountId));
		System.out.println("account:"+account);
		//购物车所需的钱大于余额
		System.out.println("购物花费:"+sc.getTotalMoney());
		System.out.println("余额:"+account.getBalance());
		if(sc.getTotalMoney() > account.getBalance()){
			errors.append("余额不足");
		}
		return errors;
	}

	//校验库存
	private StringBuffer validateStoreNumber(HttpServletRequest request) {
		StringBuffer errors = new StringBuffer("");
		ShoppingCart sc = BookStoreWebUtils.getShoppingCart(request);
		for(ShoppingCartItem item:sc.getItems()){
			int quantity = item.getQuantity();   //该本书在购物车中的数量，与数据库中的该本书的storeNumber对比
			int storeNumber = item.getBook().getStoreNumber(); //数据库中该本书的storeNumber
			if(quantity > storeNumber){
				errors.append(item.getBook().getTitle()+"库存不足<br>");
			}
		}
		return errors;
	}

	//校验用户名表单是否为空
	private StringBuffer validateFormField(String username,String accountId){
		StringBuffer errors = new StringBuffer("");
		if(username == null || username.trim().equals("")){
			errors.append("用户名不能为空<br>");
		}
		if(accountId == null || accountId.trim().equals("")){
			errors.append("账号不能为空<br>");
		}
		return errors;
	}
	
	//校验用户名和账号是否匹配
	private StringBuffer validateUser(String username,String accountId){
		StringBuffer errors = new StringBuffer("");
		//根据accountId查询出对应的Account对象
		User user = userService.getUser(username);
		if(!accountId.equals(""+user.getAccountId())){
			errors.append("用户名和账号不匹配");
		}
		return errors;
	}
	
	//Ajax更新书本的数量
	public void updateQuantity(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException{
		String idStr = request.getParameter("id");
		String quantityStr = request.getParameter("quantity");
		ShoppingCart sc = BookStoreWebUtils.getShoppingCart(request);
		int id = -1;
		int quantity = -1;
		try {
			id = Integer.parseInt(idStr);
			quantity = Integer.parseInt(quantityStr);
		} catch (NumberFormatException e) {
		}
		if(id > 0 && quantity > 0)
			bookService.updateQuantity(sc, id, quantity);
		Map<String, Object> result = new HashMap<>();
		result.put("bookNumber", sc.getBookNumber());
		result.put("totalMoney", sc.getTotalMoney());
		result.put("modifyValue", quantity);
		//把参数转化成json数据
		Gson gson = new Gson();
		String gsonStr = gson.toJson(result);
		response.setContentType("text/javascript");
		response.getWriter().println(gsonStr);
	}
	
	//删除购物车中的一本书的记录 
	public void remove(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException{
		String idString = request.getParameter("id");
		int id = -1;
		try {
			id = Integer.parseInt(idString);
		} catch (NumberFormatException e) {
		}
		if(id > 0){
			ShoppingCart sc = BookStoreWebUtils.getShoppingCart(request);
			bookService.remove(id, sc);
			//所有的书的记录一条条都删除光了
			if (sc.getBookNumber() == 0) {
				//去emptyCart页面
				response.sendRedirect(request.getContextPath() + "/empty.jsp");
				return;
			}
			request.getRequestDispatcher("/cart.jsp").forward(request, response);
		}
	}
	
	//清空购物车中的所有记录
	public void clear(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException{
		ShoppingCart sc = BookStoreWebUtils.getShoppingCart(request);
		bookService.clear(sc);
		//去emptyCart页面
		response.sendRedirect(request.getContextPath() + "/empty.jsp");
	}
	
	public void toForwardPage(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException{
		String name = request.getParameter("page");
		request.getRequestDispatcher("/" + name + ".jsp").forward(request, response);
	}
	
	//去购物车页经过的servlet处理
	public void toCartPage(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException{
		request.getRequestDispatcher("/cart.jsp").forward(request, response);
	}
	
	//加入购物车显示的url是bookServlet?method=addToCart&pageNo=1&id=3&title=Ruby&minPrice=&maxPrice=
	public void addToCart(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException{
		String idStr = request.getParameter("id");
		int id = -1;
		boolean flag = false;
		try {
			id = Integer.parseInt(idStr);
		} catch (NumberFormatException e) {
		}
		//如果id是合法的
		if (id > 0){
			ShoppingCart sc = BookStoreWebUtils.getShoppingCart(request);
			flag = bookService.addToCart(id, sc);
		}
		//成功往ShoppingCart中加书
		if(flag){
			//这里不能直接转发，不然拿不到getBooks方法中的page对象
			getBooks(request, response);
			return;
		}
		//错误的话，去error.jsp
		response.sendRedirect(request.getContextPath()+"/error.jsp");
	}
	
	public void getBook(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException{
		String idStr = request.getParameter("id");
		int id = -1;
		try {
			id = Integer.parseInt(idStr);
		} catch (NumberFormatException e) {
			
		}
		Book book = bookService.getBook(id);
		if (book == null){
			request.getRequestDispatcher("/error.jsp").forward(request, response);
			return;
		}
		request.setAttribute("book", book);
		request.getRequestDispatcher("/book.jsp").forward(request, response);
	}
	
	public void getBooks(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		String pageNostr = request.getParameter("pageNo");
		String minPriceStr = request.getParameter("minPrice");
		String maxPriceStr = request.getParameter("maxPrice");
		int pageNo = 1;
		int minPrice = 0;
		int maxPrice = Integer.MAX_VALUE;
		//有时没有传入minPrice和maxPrice参数时，会转换错误,所以使用try catch
		try {
			pageNo = Integer.parseInt(pageNostr);
		} catch (NumberFormatException e) {
		}
		try {
			minPrice = Integer.parseInt(minPriceStr);
		} catch (NumberFormatException e) {
		}
		try {
			maxPrice = Integer.parseInt(maxPriceStr);
		} catch (NumberFormatException e) {
			
		}
		CriteriaBook cb = new CriteriaBook(minPrice, maxPrice, pageNo);
		//中间使用一层BookSerive,现在只是调用BookDaoImpl的方法
		Page<Book> page = bookService.getPage(cb);
		request.setAttribute("bookpage", page);
		//来到展示图书的jsp页面books.jsp
		request.getRequestDispatcher("/books.jsp").forward(request, response);
		
	}

}
