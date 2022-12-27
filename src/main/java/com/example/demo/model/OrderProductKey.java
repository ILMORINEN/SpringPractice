package com.example.demo.model;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;

@Data
@Embeddable
public class OrderProductKey implements Serializable {
    @Column(name = "purchase_order")
    Long order;

    @Column(name = "product")
    Long product;
}
