package com.company.cpluss.inventory_service.repository;

import com.company.cpluss.inventory_service.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order, Long> {
    Order findByOrderID(String orderID);
}
