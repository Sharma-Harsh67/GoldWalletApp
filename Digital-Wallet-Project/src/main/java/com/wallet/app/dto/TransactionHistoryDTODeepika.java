package com.wallet.transaction.dto;

public class TransactionHistoryDTODeepika {

	private Integer transactionId;
	private Double quantity;
	private Integer branchId;
	private String userName;

	public TransactionHistoryDTODeepika(Integer transactionId, Double quantity, Integer branchId, String userName) {

		this.transactionId = transactionId;

		this.quantity = quantity;

		this.branchId = branchId;

		this.userName = userName;

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
