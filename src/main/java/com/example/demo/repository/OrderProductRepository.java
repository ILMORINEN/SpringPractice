package com.example.demo.repository;

import com.example.demo.model.OrderProduct;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.LinkedList;

public interface OrderProductRepository extends PagingAndSortingRepository<OrderProduct, Long> {
}
