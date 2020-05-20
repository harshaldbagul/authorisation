package com.nissandigital.components.common.authorisation.model;


import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.Valid;

import org.springframework.validation.annotation.Validated;


/**
 * Users
 */
@Validated
@javax.annotation.Generated(value = "com.nissandigital.components.common.authorisation.codegen.languages.SpringCodegen", date = "2018-11-14T08:34:28.245Z")
@Entity
@Table(name="Users")
public class Users{
 


  @EmbeddedId
  private UserId userIdRole;
  
  @Column(name="grant_access",nullable=true)
   private boolean grantAccess; 
    @Valid
    /**
     * Get Role
     * @return Role
    **/

    @Column(name="UDID",nullable=true)
    private String UDID;
    
    @Column(name="name",nullable=true)
    private String name; 

  /**
   * Get userId
   * @return userId
  **/

 


  
 
   

public Users() {
	super();
	// TODO Auto-generated constructor stub
}


public Users(UserId userIdRole, boolean grantAccess, @Valid String uDID, String name) {
	super();
	this.userIdRole = userIdRole;
	this.grantAccess = grantAccess;
	UDID = uDID;
	this.name = name;
}


public String getUDID() {
	return UDID;
}


public void setUDID(String uDID) {
	UDID = uDID;
}


public String getName() {
	return name;
}


public void setName(String name) {
	this.name = name;
}


public UserId getUserIdRole() {
	return userIdRole;
}
public void setUserIdRole(UserId userIdRole) {
	this.userIdRole = userIdRole;
}
public boolean isGrantAccess() {
	return grantAccess;
}
public void setGrantAccess(boolean grantAccess) {
	this.grantAccess = grantAccess;
}








  
}

