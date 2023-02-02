package com.company.cpluss.inventory_service.service;

import com.company.cpluss.inventory_service.model.OrderScope;
import com.company.cpluss.inventory_service.model.Orders;
import com.company.cpluss.inventory_service.repository.OrderRepository;
import com.company.cpluss.notification_service.model.Email;
import com.company.cpluss.notification_service.service.EmailService;
import com.company.cpluss.security.model.User;
import com.company.cpluss.security.repository.UserRepository;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Service;

import java.security.Principal;
import java.util.Random;


@Service
public class OrderService {

    private final OrderRepository orderRepository;
    private final UserRepository userRepository;
    private final EmailService emailService;

    public OrderService(OrderRepository orderRepository, UserRepository userRepository, EmailService emailService) {
        this.orderRepository = orderRepository;
        this.userRepository = userRepository;
        this.emailService = emailService;
    }

    @Secured({"USER","ADMIN","SUPERADMIN"})
    public Orders createOrder(Orders order, Principal principal){

        try
        {
        User user = userRepository.findByUsername(principal.getName());
        Email email;
        order.setTotalAmount(order.getQuantity() * order.getPrice());

        if(order.getOrderScope() == OrderScope.CROSS_COUNTRY) {

            order.setOrderID("CDU-" + new Random(System.currentTimeMillis()).nextInt(99999999));

            email = new Email(
                    user.getEmail(),
                    "Order placed successfully",
                    "Your order number " +order.getOrderID() +" for "+order.getQuantity()+ " " +order.getProduct().getProductName()
                            +"(s), costing a total amount of UGX " + order.getTotalAmount()
                            +" has been received successfully \n Follow the link to track your order  http://localhost:8899/orders/?id=" +order.getOrderID()
                    +" \n"+ "You will receive an order confirmation email in a period of 30 minutes \n\n"
                    +"Thank you for shopping with us, Enjoy the delightful experience"
            );

            user.getOrders().add(order);

            order.setOwner(principal.getName());

            emailService.send(email);

            return orderRepository.save(order);
        }else if(order.getOrderScope() == OrderScope.INTER_DISTRICT){

            user.getOrders().add(order);
            order.setOrderID("IDU-" + new Random(System.currentTimeMillis()).nextInt(99999999));

            email = new Email(
                    user.getEmail(),
                    "Order placed successfully",
                    "Your order number " +order.getOrderID() +" for "+order.getQuantity()+ " " +order.getProduct().getProductName()
                            +"(s), costing a total amount of UGX " + order.getTotalAmount()
                            +" has been received successfully \n Follow the link to track your order  http://localhost:8899/orders/?id=" +order.getOrderID()
                            +" \n"+ "You will receive an order confirmation email in a period of 30 minutes \n\n"
                            +"Thank you for shopping with us, Enjoy the delightful experience"
            );

            //order.setOrderID("LD-" + UUID.randomUUID().toString());
            order.setOwner(principal.getName());
            emailService.send(email);

            return orderRepository.save(order);
        }else if(order.getOrderScope() == OrderScope.INTER_NATIONAL){


            user.getOrders().add(order);
            order.setOrderID("IDU-" + new Random(System.currentTimeMillis()).nextInt(99999999));

            email = new Email(
                    user.getEmail(),
                    "Order placed successfully",
                    "Your order number " +order.getOrderID() +" for "+order.getQuantity()+ " " +order.getProduct().getProductName()
                            +"(s), costing a total amount of UGX " + order.getTotalAmount()
                            +" has been received successfully \n Follow the link to track your order  http://localhost:8899/orders/?id=" +order.getOrderID()
                            +" \n"+ "You will receive an order confirmation email in a period of 30 minutes \n\n"
                            +"Thank you for shopping with us, Enjoy the delightful experience"
            );
            order.setOwner(principal.getName());
            emailService.send(email);

            return orderRepository.save(order);
        }else if(order.getOrderScope() == OrderScope.LOCAL_DELIVERY) {

            user.getOrders().add(order);
            order.setOrderID("LDU-" + new Random(System.currentTimeMillis()).nextInt(99999999));

            email = new Email(
                    user.getEmail(),
                    "Order placed successfully",
                    "Your order number " +order.getOrderID() +" for "+order.getQuantity()+ " " +order.getProduct().getProductName()
                            +"(s), costing a total amount of UGX " + order.getTotalAmount()
                            +" has been received successfully \n Follow the link to track your order  http://localhost:8899/orders/?id=" +order.getOrderID()
                            +" \n"+ "You will receive an order confirmation email in a period of 30 minutes \n\n"
                            +"Thank you for shopping with us, Enjoy the delightful experience"
            );

            order.setOwner(principal.getName());
            emailService.send(email);
        }
            return orderRepository.save(order);

        } catch (Exception e) {
            e.printStackTrace();
        }


        return null;
    }

    @Secured({"USER","ADMIN","SUPERADMIN"})
    public Orders viewOrder(String id){
       return orderRepository.findByOrderID(id);
    }



}
