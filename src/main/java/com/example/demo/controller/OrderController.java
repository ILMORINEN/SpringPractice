package com.example.demo.controller;

import com.example.demo.model.Order;
import com.example.demo.model.Product;
import com.example.demo.repository.OrderRepository;
import com.example.demo.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.stream.Collectors;

@Controller
@RequestMapping("orders")
public class OrderController {
    @Autowired
    private OrderRepository orderRepository;
    @Autowired
    private ProductRepository productRepository;

    @GetMapping
    public String orders(Model model) {
        model.addAttribute("orders", orderRepository.findAll());
        return "orders";
    }

    @GetMapping("/addForm")
    public String addOrderForm(Model model) {
        model.addAttribute("order", new Order());
        model.addAttribute("products", productRepository.findAll());
        return "add-order";
    }

    @PostMapping("/add")
    public String addOrder(Order order) {
        orderRepository.save(order);
        return "redirect:/orders";
    }

    @GetMapping("/editForm/{id}")
    public String editOrderForm(@PathVariable Long id, Model model)
    {
        Order order = orderRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid order Id:" + id));
        model.addAttribute("order",order);
        return "update-order";
    }

    @PostMapping("/edit/{id}")
    public String updateOrder(@PathVariable Long id, Order order) {
        Order oldOrder = orderRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid order Id:" + id));
        oldOrder.setDate(order.getDate());
        orderRepository.save(oldOrder);
        return "redirect:/orders";
    }

    @GetMapping("/delete/{id}")
    public String deleteOrder(@PathVariable Long id) {
        Order order = orderRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid order Id:" + id));
        orderRepository.delete(order);
        return "redirect:/orders";
    }

}
