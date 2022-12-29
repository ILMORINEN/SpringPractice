package com.example.demo.controller;


import com.example.demo.model.Order;
import com.example.demo.model.Product;
import com.example.demo.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("products")
public class ProductController {
    @Autowired
    private ProductRepository productRepository;
    @GetMapping
    public String products(Model model){
        model.addAttribute("products", productRepository.findAll());
        return "products";
    }
    @GetMapping("/addForm")
    public String addProduct(Model model) {
        model.addAttribute("product", new Product());
        return "add-product";
    }

    @PostMapping("/add")
    public String addProductToDb(Product product) {
        productRepository.save(product);
        return "redirect:/products";
    }

    @GetMapping("/editForm/{id}")
    public String editProductForm(@PathVariable Long id, Model model)
    {
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid order Id:" + id));
        model.addAttribute("product", product);
        return "update-product";
    }

    @PostMapping("/edit/{id}")
    public String updateProduct(@PathVariable Long id, Product product) {
        Product oldProduct = productRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid order Id:" + id));
        oldProduct.setName(product.getName());
        oldProduct.setDescription(product.getDescription());
        oldProduct.setPrice(product.getPrice());
        productRepository.save(oldProduct);
        return "redirect:/products";
    }

    @GetMapping("/delete/{id}")
    public String deleteProduct(@PathVariable Long id) {
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid order Id:" + id));
        productRepository.delete(product);
        return "redirect:/products";
    }
}
