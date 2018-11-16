package com.apps.haitao.twatcher.twserver.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.apps.haitao.twatcher.twserver.utils.DbUtil;

/**
 * Servlet implementation class AssosiationServlet
 */
@WebServlet(description = "Trying To Create Association For Client...", urlPatterns = { "/CreateAssociation" })
public class AssociationServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AssociationServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//response.getWriter().append("Served at: ").append(request.getContextPath());
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/plain;charset=utf-8"); //text/html;charset=utf-8 
		String parentId = request.getParameter("parentId");
		String childPhoneNum = request.getParameter("childPhoneNum");
		if (parentId != null && childPhoneNum != null) {
			try {
				PrintWriter out = response.getWriter();
//				if (parentResult.first() && childResult.first()) {
//					out.write("创建成功!");
//				} else {
//					
//				}
				DbUtil.openQueryConn(); //建立与数据库连接的流 ResultSet类型对象 依赖于Connection的流
				ResultSet childSet = DbUtil.query("tw_user", 
						new DbUtil.QuerySet[]{
								new DbUtil.QuerySet("", "phone_num", "=", childPhoneNum, "")
						});
				//System.out.println(new DbUtil.QuerySet("", "phone_num", "=", childPhoneNum, "").toString());
//				if (childSet == null) {
//					out.write("不存在对应电话号码!");
//					return;
//				}
				String childId = "";
				if (childSet.first()) {
					if (!childSet.getBoolean("is_child")) {
						out.write("该电话不是孩子电话!");
						return;
					}
					childId =  childSet.getString("user_id");
					System.out.println(childId);
				} else {
					out.write("找不到对应电话号码!");
					return;
				}
				ResultSet parentResult = DbUtil.query("tw_user", 
						new DbUtil.QuerySet[]{
								new DbUtil.QuerySet("", "user_id", "=", "'" + parentId + "'", "")
						});
				ResultSet childResult = DbUtil.query("tw_user", 
						new DbUtil.QuerySet[]{
								new DbUtil.QuerySet("", "user_id", "=", "'" + childId + "'", "")
						});
				if (parentResult == null || childResult == null) {
					out.write("找不到相应用户!");
					return;
				}
				out.write(parentResult.first() && childResult.first() ? "创建关系成功!" : "创建关系失败!");
				DbUtil.insert("tw_user_asso", new String[] {"parent_id", "child_id"}, new String[] {"'" + parentId + "'", "'" + childId + "'"});
				System.out.println(parentId + " " + childId);
				DbUtil.closeQueryConn(); //关闭Connection流 依赖于Connection的流
			} catch (SQLException exp) {
				exp.printStackTrace();
				System.out.println("创建关系出错!");
				//System.out.println(new DbUtil.QuerySet("", "phone_num", "=", childPhoneNum, "").toString());
			}
		} else {
			PrintWriter out = response.getWriter();
			out.write("输入不能为空!");
			System.out.println("输入为空!" + parentId + " " + childPhoneNum);
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
