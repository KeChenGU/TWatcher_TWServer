package com.apps.haitao.twatcher.twserver.messages;

public class Test {
	
	private int id;
	
	private String account;
	
	private String password;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getAccount() {
		return account;
	}

	public void setAccount(String account) {
		this.account = account;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		//return super.toString();
		return "test:{'id':" + id + ", 'account':" + account + ", 'password':" + password + "}";
	}
	
	
}
