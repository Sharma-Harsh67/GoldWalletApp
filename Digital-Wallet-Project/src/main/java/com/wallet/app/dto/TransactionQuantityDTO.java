package com.wallet.app.dto;
 
public class TransactionQuantityDTO {
    public TransactionQuantityDTO(String userName, Double quantity, Long transactionId,
			Long deliveryAddressId) {
		super();
		this.userName = userName;
		this.quantity = quantity;
		this.transactionId = transactionId;
		this.deliveryAddressId = deliveryAddressId;
	}
 
	private String userName;
    private Double quantity;
    private Long transactionId;
    private Long deliveryAddressId;
 
    
 
    public String getUserName() {
        return userName;
    }
 
    public void setUserName(String userName) {
        this.userName = userName;
    }
 
    public Double getQuantity() {
        return quantity;
    }
 
    public void setQuantity(Double quantity) {
        this.quantity = quantity;
    }
 
	public Long getTransactionId() {
		return transactionId;
	}
 
	public void setTransactionId(Long transactionId) {
		this.transactionId = transactionId;
	}
 
	public Long getDeliveryAddressId() {
		return deliveryAddressId;
	}
 
	public void setDeliveryAddressId(Long deliveryAddressId) {
		this.deliveryAddressId = deliveryAddressId;
	}
}
 