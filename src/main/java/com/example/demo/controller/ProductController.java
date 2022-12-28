package com.example.demo.controller;


import com.example.demo.model.Product;
import com.example.demo.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.Banner;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class ProductController {
    @Autowired
    private ProductRepository productRepository;
    @GetMapping("/products")
    public String products(Model model){
        model.addAttribute("products", productRepository.findAll());
        return "products";
    }
    @GetMapping("/products/addProductForm")
    public String addProduct(Model model) {
        model.addAttribute("product", new Product());
        return "add-product";
    }

    @PostMapping("/products/addProduct")
    public String addProductToDb(Product product, Model model) {
        productRepository.save(product);
        return "redirect:/products";
    }


}
