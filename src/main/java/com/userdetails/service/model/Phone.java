package com.userdetails.service.model;

import java.io.Serializable;
import java.util.Objects;

public class Phone implements Serializable {
	
	private int countryCode;
	private String phone;
	private PhoneType phoneType;
	
	public int getCountryCode() {
		return countryCode;
	}
	public void setCountryCode(int countryCode) {
		this.countryCode = countryCode;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public PhoneType getPhoneType() {
		return phoneType;
	}
	public void setPhoneType(PhoneType phoneType) {
		this.phoneType = phoneType;
	}
	@Override
	public int hashCode() {
		return Objects.hash(countryCode, phone, phoneType);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!(obj instanceof Phone))
			return false;
		Phone other = (Phone) obj;
		return countryCode == other.countryCode && Objects.equals(phone, other.phone) && phoneType == other.phoneType;
	}
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Phone [countryCode=").append(countryCode).append(", phone=").append(phone)
				.append(", phoneType=").append(phoneType).append("]");
		return builder.toString();
	}
	
}
