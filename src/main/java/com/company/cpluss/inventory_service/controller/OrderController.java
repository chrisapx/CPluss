package com.company.cpluss.inventory_service.controller;

import com.company.cpluss.inventory_service.model.Order;
import com.company.cpluss.security.model.User;
import com.company.cpluss.security.repository.UserRepository;
import com.company.cpluss.inventory_service.service.OrderService;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.Nullable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

@RestController
@RequestMapping("/orders")
public class OrderController {

    private final OrderService orderService;
    private final UserRepository userRepository;

    public OrderController(OrderService orderService, UserRepository userRepository) {
        this.orderService = orderService;
        this.userRepository = userRepository;
    }


    @PostMapping
    public ResponseEntity addOrder(@RequestBody Order order, @Nullable Principal principal){
        User orderOwner = userRepository.findByUsername(principal.getName());
        order.setOwner(orderOwner);
        return ResponseEntity.ok(orderService.createOrder(order, null));
    }

    @GetMapping
    public ResponseEntity viewOrder(@RequestParam long id){
        return ResponseEntity.ok(orderService.viewOrder(id));
    }


}
