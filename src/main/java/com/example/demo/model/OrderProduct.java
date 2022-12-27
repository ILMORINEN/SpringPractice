package com.example.demo.model;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
public class OrderProduct {
    @EmbeddedId
    OrderProductKey id;

    @ManyToOne
    @MapsId("order")
    @JoinColumn(name = "purchase_order")
    Order order;

    @ManyToOne
    @MapsId("product")
    @JoinColumn(name = "product")
    Product product;

    Long amount;
}
