package com.lwb.ssh.domain;

import java.util.Date;

public class UserInfo {
	private int id;
	private String userName;
	private String userPass;
	private String userEmail;
	private String userRealName;
	private int userLevel; // 0 管理员，1普通成员
	private Date userRigisterDate;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getUserPass() {
		return userPass;
	}
	public void setUserPass(String userPass) {
		this.userPass = userPass;
	}
	public String getUserEmail() {
		return userEmail;
	}
	public void setUserEmail(String userEmail) {
		this.userEmail = userEmail;
	}
	public String getUserRealName() {
		return userRealName;
	}
	public void setUserRealName(String userRealName) {
		this.userRealName = userRealName;
	}
	public int getUserLevel() {
		return userLevel;
	}
	public void setUserLevel(int userLevel) {
		this.userLevel = userLevel;
	}
	public Date getUserRigisterDate() {
		return userRigisterDate;
	}
	public void setUserRigisterDate(Date userRigisterDate) {
		this.userRigisterDate = userRigisterDate;
	}
}
