package com.company.cpluss.inventory_service.service;

import com.company.cpluss.inventory_service.model.Order;
import com.company.cpluss.inventory_service.repository.OrderRepository;
import com.company.cpluss.security.model.User;
import com.company.cpluss.security.repository.UserRepository;
import org.springframework.lang.Nullable;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Service;

import java.security.Principal;
import java.util.Random;

@Service
public class OrderService {

    private final OrderRepository orderRepository;
    private final UserRepository userRepository;

    public OrderService(OrderRepository orderRepository, UserRepository userRepository) {
        this.orderRepository = orderRepository;
        this.userRepository = userRepository;
    }

    @Secured({"USER","ADMIN","SUPERADMIN"})
    public Order createOrder(Order order, @Nullable Principal principal){
        Random random = new Random();
        order.setOrderID(random.ints(8).toString());

        User orderOwner = userRepository.findByUsername(principal.getName());
        orderOwner.getOrders().add(order);
        return orderRepository.save(order);
    }

    @Secured({"USER","ADMIN","SUPERADMIN"})
    public Order viewOrder(long id){
       return orderRepository.findById(id).get();
    }



}
