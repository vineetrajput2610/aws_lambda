package com.userdetails.service.handler;

import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.userdetails.service.model.AccountType;
import com.userdetails.service.model.Address;
import com.userdetails.service.model.Gender;
import com.userdetails.service.model.Phone;
import com.userdetails.service.model.UserGrant;
import com.userdetails.service.model.UserType;

import software.amazon.awssdk.core.sync.RequestBody;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.PutObjectRequest;
import software.amazon.awssdk.services.s3.model.S3Exception;

public class UserDetailsServiceHandler implements RequestHandler<com.userdetails.service.handler.UserDetailsServiceHandler.UserDetailsRecords, String> {

	private static final S3Client S3_CLIENT = S3Client.builder().build();
	
	public record UserDetailsRecords(
			String id,	String firstName, 
			String lastName, Gender gender, 
			int weight, int age, 
			String email, List<Phone> phone, 
			String username, String password, 
			String role, String scope, 
			boolean is_active, String dateOfBirth, 
			String idCard, String alternateEmail, 
			AccountType accountType, List<Address> addresses, 
			UserType userType, UserGrant[] userGrants
		) {}

	@Override
	public String handleRequest(UserDetailsRecords input, Context context) {
		
		try {
			String s3bucketName = System.getenv("USERDETAILS_SERVICE_BUCKET");
        
			if (s3bucketName == null || s3bucketName.isEmpty()) {
				throw new IllegalArgumentException("USERDETAILS_SERVICE_BUCKET environment variable is not set");
			}
		
			String userDetailRecord = String.format("Id: %s\nFirstName: %s\nLastName: %s\nGender: %s\nWeight: %s\nAge: %s\nEmail: %s\nphones: %s\nUsername: %s"
				+"\npassword: %s\nrole: %s\nScope: %s\nisActive: %s\nDateOfBirth: %s\nIdCard: %s\nAlternateEmail: %s\nAccountType: %s\nAddresses: %s\nUserType: %s\nUserGrants: %s",
                input.id(), 
                input.firstName(), 
                input.lastName(), 
                input.gender().toString(),
                String.valueOf(input.weight() ),
                String.valueOf(input.age()),
                input.email(),
                input.phone().stream().map(  (elem) -> { return elem.toString(); } ).collect(Collectors.joining(",")),
                input.username(),
                input.password(),
                input.role(),
                input.scope(),
                String.valueOf(input.is_active()),
                input.dateOfBirth(),
                input.idCard(),
                input.alternateEmail(),
                input.accountType().toString(),
                input.addresses().stream().map((elem) -> elem.toString() ).collect(Collectors.joining(",")),
                input.userType().toString(),
                Arrays.asList(input.userGrants()).stream().map( (elem) -> elem.toString()).collect(Collectors.joining(","))               
			);
			String key = "userRegistered/" + input.id() + ".txt";
		
			uploadReceiptToS3(s3bucketName, key, userDetailRecord);
        
			context.getLogger().log("Successfully processed order " + input.id() + " and stored receipt in S3 bucket " + s3bucketName);

		 	} catch (Exception e) {
	            context.getLogger().log("Failed to process order: " + e.getMessage());
	            throw new RuntimeException(e);
		 	}

		
        return "Success";
	}
	
	private void uploadReceiptToS3(String bucketName, String key, String userDetailRecord) {
        try {
        	
            PutObjectRequest putObjectRequest = PutObjectRequest.builder()
                    .bucket(bucketName)
                    .key(key)
                    .build();
            
            S3_CLIENT.putObject(putObjectRequest, RequestBody.fromBytes(userDetailRecord.getBytes(StandardCharsets.UTF_8)));
            
        } catch (S3Exception e) {
            throw new RuntimeException("Failed to upload receipt to S3: " + e.awsErrorDetails().errorMessage(), e);
        }
        
    }		


}
