package com.irs.register.register.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Data;

@Data
@Document(collection = "users")
public class User {
	
	@Id
	private String id;
	
	private String email;
	
	private String pass;

}
