package com.javiz.cars_ecommerce_backend.dto.CarImageDTO;

import lombok.Data;

@Data
public class CarImageResponseDTO {
    private Long id;
    private Long fkidCar;
    private String image;
}
