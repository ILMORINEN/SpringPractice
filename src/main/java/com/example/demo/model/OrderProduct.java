package com.example.demo.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import javax.persistence.*;

@Data
@Entity
@Table(name = "order_product")
public class OrderProduct {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @ManyToOne
    @JoinColumn(name = "purchase_order")
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    Order order;

    @ManyToOne
    @JoinColumn(name = "product")
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    Product product;

    Long amount;
}
