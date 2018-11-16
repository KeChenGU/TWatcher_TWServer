package com.apps.haitao.twatcher.twserver.messages;

//import java.sql.Date;

//import com.google.gson.annotations.SerializedName;

public class UserInfo {
	
	//@SerializedName("user_id")
	private String userId;
	
	//@SerializedName("name")
	private String name;
	
	//@SerializedName("password")
	private String password;
	
	//@SerializedName("user_name")
	private String userName;
	
	//@SerializedName("gender")
	private String gender; //数据库内为ENUM类型
	
	//@SerializedName("id_card_num")
	private String idCardNum;
	
	//@SerializedName("phone_num")
	private long phoneNum;
	
	//@SerializedName("")
	private String email;
	
	//
	private String identity;
	
	//@SerializedName("is_child")
	private boolean isChild;
	
	//@SerializedName("register_time")
	private String registerTime; //原本Date
	
	public UserInfo() {
		
	}
	
	public UserInfo(String userId, String name, String password, String userName, String gender, String idCardNum,
			long phoneNum, String email, String identity, boolean isChild, String registerTime) {
		//super();
		this.userId = userId;
		this.name = name;
		this.password = password;
		this.userName = userName;
		this.gender = gender;
		this.idCardNum = idCardNum;
		this.phoneNum = phoneNum;
		this.email = email;
		this.identity = identity;
		this.isChild = isChild;
		this.registerTime = registerTime;
	}
	
	public UserInfo(String userId, String name, String password, String gender, long phoneNum, boolean isChild) {
		//super();
		this.userId = userId;
		this.name = name;
		this.password = password;
		this.gender = gender;
		this.phoneNum = phoneNum;
		this.isChild = isChild;
	}

	public String getUserId() {
		return userId;
	}
	
	public void setUserId(String userId) {
		this.userId = userId;
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public String getPassword() {
		return password;
	}
	
	public void setPassword(String password) {
		this.password = password;
	}
	
	public String getUserName() {
		return userName;
	}
	
	public void setUserName(String userName) {
		this.userName = userName;
	}
	
	public String getGender() {
		return gender;
	}
	
	public void setGender(String gender) {
		this.gender = gender;
	}
	
	public String getIdCardNum() {
		return idCardNum;
	}
	
	public void setIdCardNum(String idCardNum) {
		this.idCardNum = idCardNum;
	}
	
	public long getPhoneNum() {
		return phoneNum;
	}
	
	public void setPhoneNum(long phoneNum) {
		this.phoneNum = phoneNum;
	}
	
	public String getEmail() {
		return email;
	}
	
	public void setEmail(String email) {
		this.email = email;
	}
	
	public String getIdentity() {
		return identity;
	}
	
	public void setIdentity(String identity) {
		this.identity = identity;
	}
	
	public boolean isChild() {
		return isChild;
	}
	
	public void setChild(boolean isChild) {
		this.isChild = isChild;
	}
	
	public String getRegisterTime() {
		return registerTime;
	}
	
	public void setRegisterTime(String registerTime) {
		this.registerTime = registerTime;
	}
	
}
