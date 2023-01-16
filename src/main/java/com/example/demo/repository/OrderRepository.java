package com.example.demo.repository;

import com.example.demo.model.Order;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.Collection;

@Repository
public interface OrderRepository extends PagingAndSortingRepository<Order, Long> {

    @Query(value = "select purchase_order.id , purchase_order.orderdate \n" +
            "from order_product\n" +
            "join product on product.id = order_product.product\n" +
            "join purchase_order on purchase_order.id = order_product.purchase_order\n" +
            "where product.id = ?1\n" +
            "group by purchase_order.id",
            nativeQuery = true)
    Collection<Order> findProductHistory(Long productId);



}
