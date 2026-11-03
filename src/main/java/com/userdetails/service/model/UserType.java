package com.userdetails.service.model;

import java.io.Serializable;

public enum UserType implements Serializable {
	USER("USER"),
	ADMIN("ADMIN");
	
	private final String label;
	
	private UserType (String label) {
        this.label = label;
    }
	
	public static UserType valueOfLabel(String label) {
	    for (UserType e : values()) {
	        if (e.label.equals(label)) {
	            return e;
	        }
	    }
	    return null;
	}
	public String toString() { return this.label;}
}
