package com.javiz.cars_ecommerce_backend.dto.CarDTO;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class CarRequestDTO {
    private String name;
    private String model;
    private String year;
    private Long fkidBrand;
    private BigDecimal price;
    private Boolean for_sale;
    private Long fkidUser;
}
