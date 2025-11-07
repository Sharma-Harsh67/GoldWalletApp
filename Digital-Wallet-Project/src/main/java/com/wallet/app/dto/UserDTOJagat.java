package com.wallet.app.dto;

public class UserDTOJagat {
    private Integer userId;
    private String name;
    private String email;
    private Integer addressId;

    public UserDTOJagat() {}

    public UserDTOJagat(Integer userId, String name, String email, Integer addressId) {
        this.userId = userId;
        this.name = name;
        this.email = email;
        this.addressId = addressId;
    }

    public Integer getUserId() { return userId; }
    public void setUserId(Integer userId) { this.userId = userId; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public Integer getAddressId() { return addressId; }
    public void setAddressId(Integer addressId) { this.addressId = addressId; }
}