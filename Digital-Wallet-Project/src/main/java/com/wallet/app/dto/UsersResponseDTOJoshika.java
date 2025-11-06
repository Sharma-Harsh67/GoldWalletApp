package com.wallet.dto;


public class UsersResponseDTOJoshika {
public UsersResponseDTOJoshika(Integer userId, String name, String email) {
		super();
		
		this.name = name;
		this.email = email;
	}

private String name;
private String email;

public String getName() {
	return name;
}
public void setName(String name) {
	this.name = name;
}
public String getEmail() {
	return email;
}
public void setEmail(String email) {
	this.email = email;
}
}
