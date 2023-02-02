package com.company.cpluss.inventory_service.repository;

import com.company.cpluss.inventory_service.model.Orders;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderRepository extends JpaRepository<Orders, Long> {
    Orders findByOrderID(String orderID);
}
