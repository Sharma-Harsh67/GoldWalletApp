package com.wallet.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.wallet.app.model.Vendor;

@Repository
public interface VendorRepository extends JpaRepository<Vendor, Long> {
    
    @Query("SELECT v FROM Vendor v WHERE v.vendorName = :vendorName")
    Vendor findByVendorName(@Param("vendorName") String vendorName);
}