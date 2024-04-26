package com.tortora.ti.spring.security.jwt.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "tb_products")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String name;

}
