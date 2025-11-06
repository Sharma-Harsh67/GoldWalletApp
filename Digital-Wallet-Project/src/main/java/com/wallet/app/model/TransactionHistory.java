package com.wallet.app.model;

import java.time.LocalDateTime;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
 
 
@Entity
@Table(name = "transaction_history")
public class TransactionHistory {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "transaction_id")
	private Integer transactionId;
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "user_id")
	@JsonIgnore
	private Users user;
	@Column(name = "quantity")
	private Double quantity;
	@Column(name = "branch_id")
	private Integer branchId;
	@Column(name = "created_at")
	private LocalDateTime createdAt;
 
	public Integer getTransactionId() {
		return transactionId;
	}
 
	public void setTransactionId(Integer transactionId) {
		this.transactionId = transactionId;
	}
 
	public Users getUser() {
		return user;
	}
 
	public void setUser(Users user) {
		this.user = user;
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
 
	public LocalDateTime getCreatedAt() {
		return createdAt;
	}
 
	public void setCreatedAt(LocalDateTime createdAt) {
		this.createdAt = createdAt;
	}
 
}