package com.userdetails.service.model;

import java.io.Serializable;

public enum AddressType implements Serializable {

	HOME("HOME"),
	WORK("WORK"),
	OTHER("OTHER");
	
	private final String label;
	
	private AddressType (String label) {
        this.label = label;
    }
	
	public static AddressType valueOfLabel(String label) {
	    for (AddressType e : values()) {
	        if (e.label.equals(label)) {
	            return e;
	        }
	    }
	    return null;
	}
	public String toString() { return this.label;}
}
