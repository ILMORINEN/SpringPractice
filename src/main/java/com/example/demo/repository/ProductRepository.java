package com.example.demo.repository;

import com.example.demo.model.Order;
import com.example.demo.model.Product;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends PagingAndSortingRepository<Product, Long> {
    @Query(value= "select sum(order_product.amount) \n" +
            "from order_product\n" +
            "join product on product.id = order_product.product\n" +
            "join purchase_order on purchase_order.id = order_product.purchase_order\n" +
            "where product.id = ?1",
            nativeQuery = true)
    int getSumOfProduct(Long productId);
}
