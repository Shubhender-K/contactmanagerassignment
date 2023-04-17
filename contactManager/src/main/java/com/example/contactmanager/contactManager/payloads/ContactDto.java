package com.example.contactmanager.contactManager.payloads;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class ContactDto {

	//private int id;
	
	private String first_name;
	
	private String last_name;
	
	private String email;
	
	private String phone_number;
}
