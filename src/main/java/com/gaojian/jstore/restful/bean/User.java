package com.gaojian.jstore.restful.bean;

import java.io.Serializable;
import java.util.Date;

public class User implements Serializable {

	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = 5403188481823933712L;

	private String userName;
	
	private String password;
	
	private Date age;

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Date getAge() {
		return age;
	}

	public void setAge(Date age) {
		this.age = age;
	}
}
