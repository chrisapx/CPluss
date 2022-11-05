package com.company.cpluss.repository;

import com.company.cpluss.model.Item;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Item, Long> {
}
