package com.nissandigital.components.common.authorisation.payload;

public class APIops {
	private String apiURL;
	  private String appId;
	  private String role;
	  private String methodId;
	  private String componentID;
	public APIops(String apiURL, String appId, String role, String methodId, String componentID) {
		super();
		this.apiURL = apiURL;
		this.role = role;
		this.appId = appId;
		this.methodId = methodId;
		this.componentID = componentID;
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
	public String getComponentID() {
		return componentID;
	}
	public void setComponentID(String componentID) {
		this.componentID = componentID;
	}
	  
	  
}
