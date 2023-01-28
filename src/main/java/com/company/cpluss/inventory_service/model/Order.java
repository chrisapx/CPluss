package com.company.cpluss.inventory_service.model;

import com.company.cpluss.security.model.User;
import com.sun.istack.NotNull;
import com.sun.istack.Nullable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Nullable
    private String orderID;
    @OneToOne
    private Product product;
    @NotNull
    private float quantity;
    @NotNull
    private double price;
    @Nullable
    private double totalAmount;
    @Nullable
    private boolean shipped;
    @Nullable
    private boolean packed;
    @Nullable
    private boolean highValue;
    @Nullable
    private boolean outForDelivery;
    @Nullable
    private boolean delivered;
    @Nullable
    private boolean returned;
    @Nullable
    private boolean orderConfirmed;
    @Nullable
    @OneToOne
    private User owner;


}
