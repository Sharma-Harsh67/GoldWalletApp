package com.wallet.app.dto;

public class TransactionHistoryDTO {

	private Integer transactionId;
	private Double quantity;
	private Integer branchId;
	private String userName;
	private double amount;

	private String transactionType; // Display value from enum
	private String transactionStatus;

	public TransactionHistoryDTO(Integer transactionId, Double quantity, Integer branchId, String userName,
			double amount, String transactionType, String transactionStatus) {

		this.transactionId = transactionId;

		this.quantity = quantity;

		this.branchId = branchId;

		this.userName = userName;

		this.amount = amount;

		this.transactionType = transactionType;
		this.transactionStatus = transactionStatus;

	}

	public String getTransactionType() {
		return transactionType;
	}

	public void setTransactionType(String transactionType) {
		this.transactionType = transactionType;
	}

	public String getTransactionStatus() {
		return transactionStatus;
	}

	public void setTransactionStatus(String transactionStatus) {
		this.transactionStatus = transactionStatus;
	}

	public double getAmount() {
		return amount;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}

	public Integer getTransactionId() {
		return transactionId;
	}

	public void setTransactionId(Integer transactionId) {
		this.transactionId = transactionId;
	}

	public Double getQuantity() {
		return quantity;
	}

	public void setQuantity(Double quantity) {
		this.quantity = quantity;
	}

	public Integer getBranchId() {
		return branchId;
	}

	public void setBranchId(Integer branchId) {
		this.branchId = branchId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	// getters

}
