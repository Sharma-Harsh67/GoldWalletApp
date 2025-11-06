package com.wallet.app.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
 
import com.wallet.app.model.Users;
import com.wallet.app.model.VirtualGoldHolding;
 
@Repository
public interface VirtualGoldRepositoryJoshika extends JpaRepository<VirtualGoldHolding, Integer> {
	List<VirtualGoldHolding> findByUser(Users user);
}