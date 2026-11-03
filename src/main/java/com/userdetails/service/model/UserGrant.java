package com.userdetails.service.model;

import java.io.Serializable;

public class UserGrant implements Serializable {
	
	private String providerName;
	
	private String email;
	
	private String scope;

	public String getProviderName() {
		return providerName;
	}

	public void setProviderName(String providerName) {
		this.providerName = providerName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getScope() {
		return scope;
	}

	public void setScope(String scope) {
		this.scope = scope;
	}

}
