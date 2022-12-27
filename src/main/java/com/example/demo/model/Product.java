package com.example.demo.model;

import lombok.Data;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Set;

@Data
@Entity
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "productname")
    private String name;
    private String description;
    private BigDecimal price;
    @OneToMany(mappedBy = "product")
    private Set<OrderProduct> productOrders;
}
