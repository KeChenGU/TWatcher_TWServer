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
//Alt + Shift + S 快速选择 插入 Constructor Getter Setter 重写  重载等
//Ctrl + Shift + M 快速导入包类
//Ctrl + 鼠标点击 对于有源文档说明的类可进入查看
/**
 * Servlet implementation class RegisterServlet
 */
@WebServlet(description = "Trying To Store The Submissions...", urlPatterns="/RegisterSave")
public class RegisterServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public RegisterServlet() {
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
		response.setContentType("text/plain;charset=utf-8"); //text/html;charset=utf-8 
		String userJson = request.getParameter("userJson");
		System.out.println(userJson);
		PrintWriter out = response.getWriter();
		if (userJson != null) {
			UserInfo regUser = new Gson().fromJson(userJson, UserInfo.class);
			boolean regFlag = new UserBean().insertUser(regUser);
			//System.out.println("!!!!!!!!!!" + userJson);
			out.write(regFlag ? "注册成功!" : "注册失败!");
//			try {
//				
//			} catch (IOException exp) {
//				exp.printStackTrace();
//				System.out.println("数据处理异常！");
//			} finally {
//				if (out != null) {
//					
//				}
//			}
		} else {
			out.print("非Json请求！");
			out.write("{}");
		}
		out.flush();
		out.close();
	}

}
