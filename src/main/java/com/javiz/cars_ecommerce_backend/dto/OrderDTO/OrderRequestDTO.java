package com.javiz.cars_ecommerce_backend.dto.OrderDTO;

import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
public class OrderRequestDTO {
    private Long fkidUser;
    private Long fkidCar;
    private BigDecimal amount;
    private LocalDateTime date;
    private String address;
    private String phone;
    private Integer status;
}
