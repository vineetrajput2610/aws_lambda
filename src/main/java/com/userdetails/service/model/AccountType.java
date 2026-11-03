package com.userdetails.service.model;

import java.io.Serializable;

public enum AccountType implements Serializable {
	PRIMARY("PRIMARY"),
	SECONDARY("SECONDARY");
	
	private final String label;
	
	private AccountType (String label) {
        this.label = label;
    }
	
	public static AccountType valueOfLabel(String label) {
	    for (AccountType e : values()) {
	        if (e.label.equals(label)) {
	            return e;
	        }
	    }
	    return null;
	}

}
