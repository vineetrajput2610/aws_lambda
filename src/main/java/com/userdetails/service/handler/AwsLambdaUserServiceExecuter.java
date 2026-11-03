package com.userdetails.service.handler;

import java.util.Arrays;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.json.JsonMapper;
import com.userdetails.service.model.AccountType;
import com.userdetails.service.model.Address;
import com.userdetails.service.model.Gender;
import com.userdetails.service.model.Phone;
import com.userdetails.service.model.PhoneType;
import com.userdetails.service.model.UserDetails;
import com.userdetails.service.model.UserGrant;
import com.userdetails.service.model.UserType;


public class AwsLambdaUserServiceExecuter {

	public static void main(String[] args) throws JsonProcessingException {
		
		Phone phone = new Phone();
		phone.setCountryCode(91);
		phone.setPhone("9604076294");
		phone.setPhoneType(PhoneType.MOBILE);
		UserGrant userGrant = new UserGrant();
		userGrant.setEmail("email@hotmail.com");
		userGrant.setProviderName("Google");
		userGrant.setScope("profile,email");
		UserGrant[] UserGrantArr = new UserGrant[1];
		UserGrantArr[0] = userGrant;
		String inputStr = "";
		UserDetails userDetailsRecords = new UserDetails( );
		userDetailsRecords.setAccountType(AccountType.PRIMARY);
		userDetailsRecords.setAddresses(Arrays.asList(new Address()));
		userDetailsRecords.setAge(45);
		userDetailsRecords.setAlternateEmail("email@email.com");
		userDetailsRecords.setDateOfBirth("26-10-0000");
		userDetailsRecords.setEmail("email@email.com");
		userDetailsRecords.setGender(Gender.M);
		userDetailsRecords.setUserType(UserType.USER);

		JsonMapper mapper = new JsonMapper();
		inputStr = mapper.writeValueAsString(userDetailsRecords);
		System.out.println(inputStr);
	}

}
