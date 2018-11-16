package com.apps.haitao.twatcher.twserver.servlets;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.apps.haitao.twatcher.twserver.beans.UserBean;
import com.apps.haitao.twatcher.twserver.messages.UserInfo;
import com.google.gson.Gson;

//Alt + / 快速插入重写方法
//ex. syso Alt + / 快速输入System.out.println();
//Alt + Shift + S 快速选择 插入 Constructor Getter Setter 重写  重载等
//Ctrl + Shift + M 快速导入包类
//Ctrl + 鼠标点击 对于有源文档说明的类可进入查看
/**
 * Servlet implementation class LoginServlet
 */
@WebServlet(description = "Trying To Check The Corruptions...", urlPatterns = { "/LoginCheck" })
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LoginServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//response.getWriter().append("Served at: ").append(request.getContextPath());
		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//doGet(request, response);
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		
		String account = request.getParameter("account");
		String password = request.getParameter("password");
		PrintWriter out = response.getWriter();
		UserInfo userInfo = checkLogin(account, password);
		if (userInfo != null) {
			out.write(new Gson().toJson(userInfo));
		} else {
			if (account == null || account.equals("")) {
				out.write("非法用户！请注册账号！\n");
			} else {
				out.write("密码错误!");
			}
			
		}
		out.flush();
		out.close();
//		try {
//			
//		} catch (IOException exp) {
//			exp.printStackTrace();
//		} 
	}
	
	private UserInfo checkLogin(String account, String password) {
		return new UserBean().findUser(account, password);
	}

}
