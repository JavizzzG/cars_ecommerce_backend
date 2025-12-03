package com.javiz.cars_ecommerce_backend.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.math.BigDecimal;

@Entity
@Data
@Table(name = "bank_account")
public class BankAccount {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "fkcode_bank", nullable = false)
    private String fkcodeBank;

    @Column(nullable = false)
    private String document;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
    private BigDecimal balance;

    @Column(nullable = false)
    private Integer status;

}
