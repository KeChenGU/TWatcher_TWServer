<%@ page import="java.sql.Statement"%>
<%@ page import="java.sql.Connection"%>
<%@ page import="java.sql.SQLException"%>
<%@ page import="org.json.*" %>
<%@ page import="com.google.gson.*" %>
<%@ page import="com.apps.haitao.twatcher.twserver.messages.Test" %>
<%@ page import="com.apps.haitao.twatcher.twserver.messages.UserInfo" %>
<%@ page import="com.apps.haitao.twatcher.twserver.beans.UserBean" %>
<%@ page import="com.apps.haitao.twatcher.twserver.utils.DbUtil" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%

/* UserInfo testUser = null;
String userJson = request.getParameter("userJson");
if (userJson != null) {
	testUser = new Gson().fromJson(userJson, UserInfo.class);
	if (new UserBean().insertUser(testUser)) {
		out.print("注册成功!");
	} else {
		out.print("注册失败!");
	}
} */

Test test = null;
String testJson = request.getParameter("testJson");
if (testJson != null) {
	test = new Gson().fromJson(testJson, Test.class);
	System.out.println(testJson + "\n" + test.toString());
	Connection conn = DbUtil.getConnection();
	try {
		String sql = "INSERT INTO test(account, password) VALUES";
		sql += "('" + test.getAccount() + "', " + test.getPassword() + ")";
		Statement statement = conn.createStatement();
		int count = statement.executeUpdate(sql);
		if (count > 0) {
			out.print(test.toString() + "<br/>");
			out.print("插入成功!");
		}
		System.out.println("sql = " + sql);
	} catch (SQLException exp) {
		exp.printStackTrace();
	}
} else {
	out.print("Json字符串为空!");
}

%>
