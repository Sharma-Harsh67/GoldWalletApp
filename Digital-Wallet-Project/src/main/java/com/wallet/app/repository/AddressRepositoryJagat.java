package com.wallet.app.repository;

import com.wallet.app.model.AddressJagat;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AddressRepositoryJagat extends JpaRepository<AddressJagat, Integer> {
    // additional queries if needed
}