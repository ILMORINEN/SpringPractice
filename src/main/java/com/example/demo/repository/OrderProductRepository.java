package com.example.demo.repository;

import com.example.demo.model.OrderProduct;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface OrderProductRepository extends PagingAndSortingRepository<OrderProduct, Long> {

}
