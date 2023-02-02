package com.company.cpluss.inventory_service.repository;

import com.company.cpluss.inventory_service.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {
    void deleteByProductName(String productName);
}
