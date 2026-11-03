package com.userdetails.service.model;

import java.io.Serializable;

public enum Gender implements Serializable {
	M("MALE"),
	F("FEMALE");
	
	private final String label;
	
	private Gender (String label) {
        this.label = label;
    }
	
	public static Gender valueOfLabel(String label) {
	    for (Gender e : values()) {
	        if (e.label.equals(label)) {
	            return e;
	        }
	    }
	    return null;
	}
	public String toString() { return this.label;}
}
