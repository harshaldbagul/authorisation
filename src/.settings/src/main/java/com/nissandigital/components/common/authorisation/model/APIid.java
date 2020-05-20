package com.nissandigital.components.common.authorisation.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;

import org.springframework.beans.factory.annotation.Autowired;

@Embeddable
public class APIid implements Serializable{
		

	
	//api.getAppId()+id
	public static int count;
	//String apiID;
	 @Column(name="Api_URL",nullable=false)
	  private String apiURL;


	  @Column(name="Role",nullable=false)
	  private String role;
	  
	  @Column(name="App_Id",nullable=false)
	  private String appId;
	  
	  @Column(name="Method_Type",nullable=false)
	  private String methodId;

	public static int getCount() {
		return count;
	}

	public static void setCount(int count) {
		APIid.count = count;
	}

	public String getApiURL() {
		return apiURL;
	}

	public void setApiURL(String apiURL) {
		this.apiURL = apiURL;
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

	public String getMethodId() {
		return methodId;
	}

	public void setMethodId(String methodId) {
		this.methodId = methodId;
	}
	  

}
