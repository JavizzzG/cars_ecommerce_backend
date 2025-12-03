package com.javiz.cars_ecommerce_backend.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Data
@Table(name = "payment")
public class Payment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String fkidOrder;

    @Column(nullable = false)
    private String amount;

    @Column
    private String fkcodeBank;

    @Column(nullable = false)
    private Integer status;

    @Column
    private LocalDateTime created_at;

    @Column
    private LocalDateTime updated_at;

}
