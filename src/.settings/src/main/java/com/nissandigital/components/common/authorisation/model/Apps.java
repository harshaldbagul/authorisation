package com.nissandigital.components.common.authorisation.model;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.util.UUID;
import org.springframework.validation.annotation.Validated;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.Valid;
import javax.validation.constraints.*;

/**
 * Apps
 */
@Validated
@javax.annotation.Generated(value = "com.nissandigital.components.common.authorisation.codegen.languages.SpringCodegen", date = "2018-11-14T08:34:28.245Z")
@Entity
@Table(name="App")
public class Apps   {
	@Id
  @Column(name="AppId",nullable=false)
  private String appId;

  @Column(name="callbackURL",nullable=false)
  private String callbackURL;

  @Column(name="expiry_time",nullable=false)
  @org.hibernate.annotations.ColumnDefault("28800")
  private double expiryTime;
  public Apps appId(String appId) {
    this.appId = appId;
    return this;
  }

  /**
   * Get appId
   * @return appId
  **/
  @ApiModelProperty(example = "d290f1ee-6c54-4b01-90e6-d701748f0851", required = true, value = "")
  @NotNull

  @Valid

  public String getAppId() {
    return appId;
  }

  public void setAppId(String appId) {
    this.appId = appId;
  }

  public Apps callbackURL(String callbackURL) {
    this.callbackURL = callbackURL;
    return this;
  }

  /**
   * Get callbackURL
   * @return callbackURL
  **/
  @ApiModelProperty(example = "www.aws.nissan.com/login", required = true, value = "")
  @NotNull


  public String getCallbackURL() {
    return callbackURL;
  }

  public void setCallbackURL(String callbackURL) {
    this.callbackURL = callbackURL;
  }


  public double getExpiryTime() {
	return expiryTime;
}

public void setExpiryTime(double expiryTime) {
	this.expiryTime = expiryTime;
}

public Apps() {
	super();
	// TODO Auto-generated constructor stub
}

public Apps(String appId, String callbackURL,double expiryTime) {
	super();
	this.appId = appId;
	this.callbackURL = callbackURL;
	this.expiryTime=expiryTime;
}
  
  
}

