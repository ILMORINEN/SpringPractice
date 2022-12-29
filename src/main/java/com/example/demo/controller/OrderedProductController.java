package com.example.demo.controller;

import com.example.demo.model.Order;
import com.example.demo.model.OrderProduct;
import com.example.demo.model.Product;
import com.example.demo.repository.OrderProductRepository;
import com.example.demo.repository.OrderRepository;
import com.example.demo.repository.ProductRepository;
import org.aspectj.weaver.ast.Or;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.stream.Collectors;

@Controller
@RequestMapping("orders/orderedProducts/{id}")
public class OrderedProductController {
    @Autowired
    private OrderRepository orderRepository;
    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private OrderProductRepository orderProductRepository;
    @GetMapping()
    public String orderedProducts(@PathVariable Long id, Model model){
        Order order = orderRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid order Id:" + id));;
        model.addAttribute("order", order);
        var orderProducts = order.getOrderProducts();
        model.addAttribute("orderproducts", orderProducts);
        return "orderproduct";
    }

    @GetMapping("/addForm")
    public String addOrderedProductForm(@PathVariable Long id, Model model){
        Order order = orderRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid order Id:" + id));
        model.addAttribute("products",productRepository.findAll());
        model.addAttribute("order", order);
        model.addAttribute("orderProduct", new OrderProduct());
        return "add-orderproduct";
    }
    @PostMapping("/add")
    public String addOrderedProduct(@PathVariable Long id, OrderProduct newOrderProduct){
        Order order = orderRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid order Id:" + id));
        order.getOrderProducts().add(newOrderProduct);
        orderRepository.save(order);
        return "redirect:/orders/orderedProducts/"+ id.toString();
    }
    @GetMapping("editForm/{opid}")
    public String editOrderedProductForm(@PathVariable("id") Long orderId, @PathVariable("opid") Long orderedProductId, Model model){
        Order order = orderRepository.findById(orderId)
                .orElseThrow(() -> new IllegalArgumentException("Invalid order Id:" + orderId));

        OrderProduct orderProduct = orderProductRepository.findById(orderedProductId)
                .orElseThrow(() -> new IllegalArgumentException("Invalid order product Id:" + orderedProductId));

        model.addAttribute("order",order);
        model.addAttribute("orderproduct",orderProduct);
        model.addAttribute("products",productRepository.findAll());

        return "update-orderproduct";
    }
    @PostMapping("/edit/{opid}")
    public String updateOrderedProduct(@PathVariable("id") Long orderId, @PathVariable("opid") Long orderedProductId, OrderProduct orderProduct) {
        OrderProduct oldOrderProduct = orderProductRepository.findById(orderedProductId)
                .orElseThrow(() -> new IllegalArgumentException("Invalid order product Id:" + orderedProductId));
        oldOrderProduct.setProduct(orderProduct.getProduct());
        oldOrderProduct.setAmount(orderProduct.getAmount());
        orderProductRepository.save(oldOrderProduct);
        return "redirect:/orders/orderedProducts/" + orderId.toString();
    }

    @GetMapping("/delete/{opid}")
    public String deleteOrder(@PathVariable("id") Long orderId, @PathVariable("opid") Long orderedProductId) {
        OrderProduct orderProduct = orderProductRepository.findById(orderedProductId)
                .orElseThrow(() -> new IllegalArgumentException("Invalid order product Id:" + orderedProductId));
        orderProductRepository.delete(orderProduct);
        return "redirect:/orders/orderedProducts/" + orderId.toString();
    }
}
