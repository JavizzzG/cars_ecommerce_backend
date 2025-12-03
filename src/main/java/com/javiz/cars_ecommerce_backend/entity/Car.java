package com.javiz.cars_ecommerce_backend.entity;

import jakarta.persistence.*;
import lombok.Data;
import java.math.BigDecimal;

@Entity
@Data
@Table(name = "car")
public class Car {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String model;

    @Column(nullable = false)
    private String year;

    @Column(name = "fkid_brand", nullable = false)
    private Long fkidBrand;

    @Column(nullable = false)
    private BigDecimal price;

    @Column(nullable = false)
    private Boolean for_sale;

    @Column(name = "fkid_user", nullable = false)
    private Long fkidUser;
}
