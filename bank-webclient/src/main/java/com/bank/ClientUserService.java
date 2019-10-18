package com.bank;

import org.springframework.http.ResponseEntity;

public interface ClientUserService {

	public String login(User user);

	public ResponseEntity<Integer> adduserdetails(User user);
	
	public ResponseEntity<String> registeruserdetails(PersonalDetails personalDetails); 
	
}
