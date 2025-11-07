package com.wallet.app.dto;
 
import java.time.LocalDateTime;
 
public class UsersDTOKiranmayee {
	private Integer userId;
	private String name;
    private String email;
    private Integer addressId;
    private double balance;
    private LocalDateTime createdAt;
 
    public UsersDTOKiranmayee(Integer userId, String name, String email, Integer addressId, double balance,
			LocalDateTime createdAt) {
		super();
		this.userId = userId;
		this.name = name;
		this.email = email;
		this.addressId = addressId;
		this.balance = balance;
		this.createdAt = createdAt;
	}
  
    public Integer getUserId() {
		return userId;
	}
 
	public void setUserId(Integer userId) {
		this.userId = userId;
	}
 
	public Integer getAddressId() {
		return addressId;
	}
 
	public void setAddressId(Integer addressId) {
		this.addressId = addressId;
	}
 
	public double getBalance() {
		return balance;
	}
 
	public void setBalance(double balance) {
		this.balance = balance;
	}
 
	public LocalDateTime getCreatedAt() {
		return createdAt;
	}
 
	public void setCreatedAt(LocalDateTime createdAt) {
		this.createdAt = createdAt;
	}
 
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
 