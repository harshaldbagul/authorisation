package com.nissandigital.components.common.authorisation.model;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import org.springframework.validation.annotation.Validated;

import io.swagger.annotations.ApiModelProperty;

/**
 * APIs
 */
@Validated
@javax.annotation.Generated(value = "com.nissandigital.components.common.authorisation.codegen.languages.SpringCodegen", date = "2018-11-14T08:34:28.245Z")

@Entity
@Table(name = "API")
public class API   {
	
	
  
  
	
  @EmbeddedId
  private APIid idRole;



	 @Column(name="ComponentID",nullable=false)
	  private String componentID;
  /**
   * Get apiId
   * @return apiId
  **/
 
public APIid getIdRole() {
	return idRole;
}

public void setIdRole(APIid idRole) {
	this.idRole = idRole;
}


public API() {
	super();
	// TODO Auto-generated constructor stub
}

public API( APIid idRole, String componentID) {
	super();
	
	this.idRole = idRole;
	this.componentID = componentID;
}

public String getComponentID() {
	return componentID;
}

public void setComponentID(String componentID) {
	this.componentID = componentID;
}


  
}

