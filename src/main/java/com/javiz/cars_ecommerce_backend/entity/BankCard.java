package com.javiz.cars_ecommerce_backend.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.math.BigDecimal;

@Entity
@Data
@Table(name = "bank_card")
public class BankCard {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "fkcode_bank", nullable = false)
    private String fkcodeBank;

    @Column(nullable = false)
    private String number;

    @Column(nullable = false)
    private String cvc;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String expire_date;

    @Column(nullable = false)
    private BigDecimal balance;

    @Column(nullable = false)
    private Integer status;

}
