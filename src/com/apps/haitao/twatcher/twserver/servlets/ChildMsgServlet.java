package com.apps.haitao.twatcher.twserver.servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

//Alt + / 快速插入重写方法
//Alt + Shift + S 快速选择 插入 Constructor Getter Setter 重写  重载等
//Ctrl + Shift + M 快速导入包类
//Ctrl + 鼠标点击 对于有源文档说明的类可进入查看
/**
 * Servlet implementation class GetChildMsgServlet
 */
@WebServlet(description = "Trying To Handle ChildMsg From Client...", urlPatterns = { "/HandleChildMsg" })
public class ChildMsgServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ChildMsgServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
