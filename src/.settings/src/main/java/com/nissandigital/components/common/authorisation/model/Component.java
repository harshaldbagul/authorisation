/*package com.nissandigital.components.common.authorisation.model;


	import javax.persistence.Column;
	import javax.persistence.EmbeddedId;
	import javax.persistence.Entity;
	import javax.persistence.Table;

	import org.springframework.validation.annotation.Validated;

	@Entity
	@Table(name = "Component")
	public class Component {
		
		
	  @Column(name="App_Id",nullable=false)
	  private String appId;
	  
		
	  @EmbeddedId
	  private ComponentId IdComponentRole;

	  public String getAppId() {
		return appId;
	}


	public void setAppId(String appId) {
		this.appId = appId;
	}


	public ComponentId getIdComponentRole() {
		return IdComponentRole;
	}


	public void setIdComponentRole(ComponentId idComponentRole) {
		IdComponentRole = idComponentRole;
	}


	public Component() {
		super();
	}


	public Component(String appId, ComponentId idComponentRole) {
		super();
		this.appId = appId;
		IdComponentRole = idComponentRole;
	}
	

}*/
