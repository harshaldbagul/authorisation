package com.nissandigital.components.common.authorisation.payload;

public class apiPOJO {

	  private String apiURL;
	 private ApiRole[] roles;
	 
	  private String role;
	  private String appId;
	  private String methodId;
	  private String componentID;
	public apiPOJO() {
		super();
		// TODO Auto-generated constructor stub
	}
	public apiPOJO(String apiURL, ApiRole[] roles,String appId) {
		super();
		this.apiURL = apiURL;
		this.roles = roles;
		this.appId = appId;
	}
	public apiPOJO(String apiURL, String role, String appId, String methodId, String componentID) {
		
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
	public ApiRole[] getRoles() {
		return roles;
	}
	public void setRoles(ApiRole[] roles) {
		this.roles = roles;
	}
	


}
