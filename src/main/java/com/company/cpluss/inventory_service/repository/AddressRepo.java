package com.company.cpluss.inventory_service.repository;

import com.company.cpluss.security.model.Address;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AddressRepo extends JpaRepository<Address, Long> {
}
