package com.nissandigital.components.common.authorisation.payload;

public class UserPOJO {
		
	  private String userId;
	   
	   private String appId;
	   private String role;
	   private boolean grantAccess; 

	    
		public UserPOJO() {
			super();
			// TODO Auto-generated constructor stub
		}

		public UserPOJO(String userId, String appId, String role, boolean grantAccess) {
			super();
			this.userId = userId;
			this.appId = appId;
			this.role = role;
			this.grantAccess = grantAccess;
		}

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

		public boolean isGrantAccess() {
			return grantAccess;
		}

		public void setGrantAccess(boolean grantAccess) {
			this.grantAccess = grantAccess;
		} 
	    
	    

}
