package com.example.demo.controller;

import com.example.demo.model.Order;
import com.example.demo.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.Banner;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;

@Controller
public class OrderController {
    @Autowired
    private OrderRepository orderRepository;

    @GetMapping("/orders")
    public String orders(Model model) {
        model.addAttribute("orders", orderRepository.findAll());
        return "orders";
    }

    @GetMapping("/orders/addForm")
    public String addOrder(Model model) {
        model.addAttribute("order", new Order());
        return "add-order";
    }

    @PostMapping("/orders/addOrder")
    public String addOrderToDb(Order order, Model model) {
        orderRepository.save(order);
        return "redirect:/orders";
    }


}
