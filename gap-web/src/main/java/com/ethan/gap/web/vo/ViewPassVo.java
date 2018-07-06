package com.ethan.gap.web.vo;

import java.io.Serializable;

public class ViewPassVo implements Serializable{
	private static final long serialVersionUID = 2153107088706497780L;
	
	private String userId;
	private String userName;
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("ViewPassVo [userId=").append(userId)
				.append(", userName=").append(userName).append("]");
		return builder.toString();
	}
}
