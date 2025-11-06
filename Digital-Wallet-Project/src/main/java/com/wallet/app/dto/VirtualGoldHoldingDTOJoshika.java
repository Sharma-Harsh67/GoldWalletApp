package com.wallet.dto;



public class VirtualGoldHoldingDTOJoshika {

private Integer holdingId;
private Integer branchId;
private Double quantity;



public VirtualGoldHoldingDTOJoshika(Integer holdingId, Double quantity, Integer branchId) {
this.holdingId = holdingId;

this.quantity = quantity;
this.branchId = branchId;
}

public Integer getHoldingId() { return holdingId; }
public void setHoldingId(Integer holdingId) { this.holdingId = holdingId; }

public Integer getBranchId() { return branchId; }
public void setBranchId(Integer branchId) { this.branchId = branchId; }

public Double getQuantity() { return quantity; }
public void setQuantity(Double quantity) { this.quantity = quantity; }
}
