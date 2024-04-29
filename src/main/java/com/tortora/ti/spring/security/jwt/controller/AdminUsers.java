package com.tortora.ti.spring.security.jwt.controller;

import com.tortora.ti.spring.security.jwt.dto.RequestResponse;
import com.tortora.ti.spring.security.jwt.entity.Product;
import com.tortora.ti.spring.security.jwt.repository.ProductRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestController
public class AdminUsers {

    private final ProductRepository repository;

    public AdminUsers(ProductRepository repository) {
        this.repository = repository;
    }

    @GetMapping("/public/product")
    public ResponseEntity<Object> getAllProducts() {
        return ResponseEntity.ok(repository.findAll());
    }

    @PostMapping("/admin/save-product")
    public ResponseEntity<Product> saveProduct(@RequestBody RequestResponse product) {
        Product productToSave = new Product();
        productToSave.setName(product.getName());
        return ResponseEntity.ok(repository.save(productToSave));
    }

    @GetMapping("/user/alone")
    public ResponseEntity<Object> userAlone() {
        return ResponseEntity.ok("Users alone can access this API only");
    }

    @GetMapping("/admin-user/both")
    public ResponseEntity<Object> userAndAdmin() {
        return ResponseEntity.ok("Users and Admin can access this API");
    }

}
