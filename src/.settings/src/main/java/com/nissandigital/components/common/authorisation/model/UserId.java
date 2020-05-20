package com.nissandigital.components.common.authorisation.model;


import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;
@Embeddable
public class UserId implements Serializable{

	  @Column(name="userId",nullable=false)
	  private String userId;


	  @Column(name="Role",nullable=false)
	  private String role;
	  
	  @Column(name="appId",nullable=false)
	  private String appId;

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public String getAppId() {
		return appId;
	}

	public void setAppId(String appId) {
		this.appId = appId;
	}
	  
	  
}