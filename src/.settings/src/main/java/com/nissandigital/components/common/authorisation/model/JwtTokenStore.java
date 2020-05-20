package com.nissandigital.components.common.authorisation.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="JwtTokenStore")
public class JwtTokenStore {

	@Id
	@Column(name="userId",nullable=false)
	private String userId;
	
	@Column(name="JwtId", nullable=false)
	private String JwtId;
	
	@Column(name="JwtToken",nullable=false)
	private String JwtToken;
	
	@Column(name="State",nullable=false)
	private String state;

	public JwtTokenStore(String userId, String jwtId, String jwtToken, String state) {
		super();
		this.userId = userId;
		JwtId = jwtId;
		JwtToken = jwtToken;
		this.state = state;
	}
	
	public JwtTokenStore() {
		
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getJwtId() {
		return JwtId;
	}

	public void setJwtId(String jwtId) {
		JwtId = jwtId;
	}

	public String getJwtToken() {
		return JwtToken;
	}

	public void setJwtToken(String jwtToken) {
		JwtToken = jwtToken;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	@Override
	public String toString() {
		return "JwtTokenStore [userId=" + userId + ", JwtId=" + JwtId + ", JwtToken=" + JwtToken + ", state=" + state
				+ "]";
	}
	
	
	
}
