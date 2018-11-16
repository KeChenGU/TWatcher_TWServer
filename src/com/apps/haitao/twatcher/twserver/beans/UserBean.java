package com.apps.haitao.twatcher.twserver.beans;

import java.sql.Connection;
//import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.apps.haitao.twatcher.twserver.messages.UserInfo;
import com.apps.haitao.twatcher.twserver.utils.DbUtil;

public class UserBean {
	
	public UserInfo findUser(String userId) {
		Connection conn = DbUtil.getConnection();
		try {
			String sql = "SELECT * FROM tw_user WHERE user_id = " + userId;
			Statement statement = conn.createStatement();
			ResultSet resultSet = statement.executeQuery(sql);
			if (resultSet.first()) {
				//String _userId = resultSet.getString("user_id");
				String name = resultSet.getString("name");
				String password = resultSet.getString("password");
				String userName = resultSet.getString("user_name");
				String gender = resultSet.getString("gender");
				String idCardNum = resultSet.getString("id_card_num");
				long phoneNum = resultSet.getLong("phone_num");
				String email = resultSet.getString("email");
				String identity = resultSet.getString("identity");
				boolean isChild = resultSet.getBoolean("is_child");
				String registerTime = resultSet.getString("register_time");
				if (resultSet.next()) {
					System.out.println("存在多个相同用户，请验证数据库！");
				}
				return new UserInfo(userId, name, password,
						userName, gender, idCardNum,
						phoneNum, email, identity,
						isChild, registerTime);
			}
		} catch (SQLException exp) {
			exp.printStackTrace();
			System.out.println("数据库查找用户异常！");
		} finally {
			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException exp) {
					exp.printStackTrace();
				}
			}
		}
		return null;
	}
	
	public UserInfo findUser(long phoneNum) {
		Connection conn = DbUtil.getConnection();
		try {
			String sql = "SELECT * FROM tw_user WHERE phone_num = " + phoneNum;
			Statement statement = conn.createStatement();
			ResultSet resultSet = statement.executeQuery(sql);
			if (resultSet.first()) {
				String userId = resultSet.getString("user_id");
				String name = resultSet.getString("name");
				String password = resultSet.getString("password");
				String userName = resultSet.getString("user_name");
				String gender = resultSet.getString("gender");
				//long idCardNum = resultSet.getLong("id_card_num");
				String idCardNum = resultSet.getString("id_card_num");
				//long _phoneNum = resultSet.getLong("phone_num");
				String email = resultSet.getString("email");
				String identity = resultSet.getString("identity");
				boolean isChild = resultSet.getBoolean("is_child");
				//Date registerTime = resultSet.getDate("register_time");
				String registerTime = resultSet.getString("register_time");
				if (resultSet.next()) {
					System.out.println("存在多个相同用户，请验证数据库！");
				}
				return new UserInfo(userId, name, password,
						userName, gender, idCardNum,
						phoneNum, email, identity,
						isChild, registerTime);
			}
		} catch (SQLException exp) {
			exp.printStackTrace();
			System.out.println("数据库查找用户异常！");
		} finally {
			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException exp) {
					exp.printStackTrace();
				}
			}
		}
		return null;
	}
	
	public UserInfo findUser(Object account) {
		//String userId = null;
		//Long phoneNum = null;
		if (account instanceof String) {
			account = (String)account;
		} else if (account instanceof Long) {
			account = (Long)account;
		}
		Connection conn = DbUtil.getConnection();
		try {
			String sql = "SELECT * FROM tw_user WHERE phone_num = " + account + " OR email = " + account;
			Statement statement = conn.createStatement();
			ResultSet resultSet = statement.executeQuery(sql);
			if (resultSet.first()) {
				String userId = resultSet.getString("user_id");
				String name = resultSet.getString("name");
				String password = resultSet.getString("password");
				String userName = resultSet.getString("user_name");
				String gender = resultSet.getString("gender");
				String idCardNum = resultSet.getString("id_card_num");
				long phoneNum = resultSet.getLong("phone_num");
				String email = resultSet.getString("email");
				String identity = resultSet.getString("identity");
				boolean isChild = resultSet.getBoolean("is_child");
				String registerTime = resultSet.getString("register_time");
				if (resultSet.next()) {
					System.out.println("存在多个相同用户，请验证数据库！");
				}
				return new UserInfo(userId, name, password,
						userName, gender, idCardNum,
						phoneNum, email, identity,
						isChild, registerTime);
			}
		} catch (SQLException exp) {
			exp.printStackTrace();
			System.out.println("数据库查找用户异常！");
		} finally {
			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException exp) {
					exp.printStackTrace();
				}
			}
		}
		return null;
	}
	
	public boolean insertUser(UserInfo info) {
		Connection conn = DbUtil.getConnection();
		try {
			String sql = "INSERT INTO tw_user"
					+ "(user_id, name, password, gender, phone_num, is_child, register_time)"
					+ "VALUES(?, ?, ?, ?, ?, ?, ?);";
			PreparedStatement statement = conn.prepareStatement(sql);
			int i = 1;
			statement.setString(i++, info.getUserId());
			statement.setString(i++, info.getName());
			statement.setString(i++, info.getPassword());
			statement.setString(i++, info.getGender());
			statement.setLong(i++, info.getPhoneNum());
			statement.setBoolean(i++, info.isChild());
			statement.setString(i, info.getRegisterTime());
			System.out.printf("sql = %s\n", statement.toString());
			statement.executeUpdate(); 
		} catch (SQLException exp) {
			exp.printStackTrace();
			System.out.println("新增失败！");
			return false;
		} finally {
			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException exp) {
					exp.printStackTrace();
				}
			}
		}
		return true;
	}
	
	public UserInfo findUser(String account, String password) {
		Connection conn = DbUtil.getConnection();
		try {
			String sql = "SELECT * FROM tw_user WHERE (phone_num = ? OR email = ?) AND password = ?";
//			String sql0 = "SELET * FROM tw_user "
//					+ "WHERE password IN"
//					+ "(SELECT password FROM tw_user WHERE phone_num = ? OR email = ?)";
			PreparedStatement statement = conn.prepareStatement(sql);
			int i = 1;
			statement.setString(i++, account);
			statement.setString(i++, account);
			statement.setString(i, password);
			System.out.printf("sql = %s\n", statement.toString());
			ResultSet resultSet = statement.executeQuery();
			if (resultSet.first()) {
				String userId = resultSet.getString("user_id");
				String name = resultSet.getString("name");
				//String _password = resultSet.getString("password");
				String userName = resultSet.getString("user_name");
				String gender = resultSet.getString("gender");
				String idCardNum = resultSet.getString("id_card_num");
				long phoneNum = resultSet.getLong("phone_num");
				String email = resultSet.getString("email");
				String identity = resultSet.getString("identity");
				boolean isChild = resultSet.getBoolean("is_child");
				String registerTime = resultSet.getString("register_time");
				if (resultSet.next()) {
					System.out.println("存在多个相同用户，请验证数据库！");
				}
				return new UserInfo(userId, name, password,
						userName, gender, idCardNum,
						phoneNum, email, identity,
						isChild, registerTime);
			}
		} catch (SQLException exp) {
			exp.printStackTrace();
		}
		return null;
	}
	
}
