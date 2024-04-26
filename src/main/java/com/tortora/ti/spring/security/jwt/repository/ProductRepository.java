package com.tortora.ti.spring.security.jwt.repository;

import com.tortora.ti.spring.security.jwt.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Integer> {



}
