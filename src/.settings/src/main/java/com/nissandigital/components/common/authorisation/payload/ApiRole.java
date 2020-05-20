package com.nissandigital.components.common.authorisation.payload;

public class ApiRole {

	private String[] methodType;
	private String componentID;
	private String role;
	public String[] getMethodType() {
		return methodType;
	}
	public void setMethodType(String[] methodType) {
		this.methodType = methodType;
	}
	public String getComponentID() {
		return componentID;
	}
	public void setComponentID(String componentID) {
		this.componentID = componentID;
	}
	public String getRole() {
		return role;
	}
	public void setRole(String role) {
		this.role = role;
	}
	
}
