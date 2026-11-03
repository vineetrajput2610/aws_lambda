package com.userdetails.service.model;

public enum PhoneType {
	MOBILE("MOBILE"),
	HOMEPHONE("HOMEPHONE"),
	WORKPHONE("WORKPHONE");;
	private final String label;
	
	private PhoneType (String label) {
        this.label = label;
    }
	
	public static PhoneType valueOfLabel(String label) {
	    for (PhoneType e : values()) {
	        if (e.label.equals(label)) {
	            return e;
	        }
	    }
	    return null;
	}
	public String toString() { return this.label;}
}
