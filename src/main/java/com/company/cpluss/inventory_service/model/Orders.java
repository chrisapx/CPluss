package com.company.cpluss.inventory_service.model;

import com.company.cpluss.security.model.User;
import com.sun.istack.NotNull;
import com.sun.istack.Nullable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
public class Orders {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Nullable
    private String orderID;
    @OneToOne(targetEntity = Product.class, cascade = CascadeType.PERSIST)
    private Product product;
    @NotNull
    private float quantity;
    @NotNull
    private double price;

    private double totalAmount; // = price * quantity;

    private String owner;

    private OrderScope orderScope;

    private OrderStatus orderStatus;

}
