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
@Table(name = "virtual_gold_holdings")
public class VirtualGoldHolding {
 
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="holding_id")
	private Integer holdingId;
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="user_id")
	@JsonIgnore
	private Users user;
	@Column(name="branch_id")
	private Integer branchId;
	private double quantity;
	@Column(name="created_at")
	private LocalDateTime created_at;
 
	public VirtualGoldHolding() {}
 
	public Integer getHoldingId() {
		return holdingId;
	}
 
	public void setHoldingId(Integer holding_id, Integer holdingId) {
		this.holdingId = holdingId;
	}
 
	public Integer getUser_id() {
		return getUser_id();
	}
 
	public void setUser_id(Users user_id) {
		this.user = user_id;
	}
 
	public Integer getBranchId() {
		return branchId;
	}
 
	public void setBranchId(Integer branchId) {
		this.branchId = branchId;
	}
 
	public double getQuantity() {
		return quantity;
	}
 
	public void setQuantity(double quantity) {
		this.quantity = quantity;
	}
 
	public LocalDateTime getCreated_at() {
		return created_at;
	}
 
	public void setCreated_at(LocalDateTime created_at) {
		this.created_at = created_at;
	}
 
 
}
