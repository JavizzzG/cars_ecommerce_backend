package com.javiz.cars_ecommerce_backend.dto.CarDetailDTO;

import lombok.Data;

@Data
public class CarDetailResponseDTO {
    private Long id;
    private Long fkidCar;
    private String door;
    private String seat;
    private String motor;
    private Integer hp;
    private Integer km;
    private Integer max_velocity;
    private Integer torque;
    private Integer fuel;
    private Integer hybrid;
    private Integer autonomy;
    private Integer brake;
    private Integer modified;
    private String description;
}
