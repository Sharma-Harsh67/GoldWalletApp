package com.wallet.app.dto;
 
public class VirtualGoldHoldingDTO {
	private String userName;	
	private Integer holdingId;
	private Integer branchId;
	private Double quantity;
 
public VirtualGoldHoldingDTO(String userName, Integer holdingId, Integer branchId, double quantity) {
	super();
	this.userName = userName;
	this.holdingId = holdingId;
	this.branchId = branchId;
	this.quantity = quantity;
}
 
public String getUserName() {
	return userName;
}
 
public void setUserName(String userName) {
	this.userName = userName;
}
 
public Integer getHoldingId() {
	return holdingId;
}
 
public void setHoldingId(Integer holdingId) {
	this.holdingId = holdingId;
}
 
public VirtualGoldHoldingDTO() {
	super();
}

public Integer getBranchId() {
	return branchId;
}
 
public void setBranchId(Integer branchId) {
	this.branchId = branchId;
}
 
public Double getQuantity() {
	return quantity;
}
 
public void setQuantity(Double quantity) {
	this.quantity = quantity;
}
 
 
 
 
 
 
 
}
 