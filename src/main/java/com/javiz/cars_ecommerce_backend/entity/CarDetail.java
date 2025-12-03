package com.javiz.cars_ecommerce_backend.entity;

import jakarta.persistence.*;
import lombok.Data;
@Entity
@Data
@Table(name = "car_detail")
public class CarDetail {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    public Long fkidCar;

    @Column
    public String door;

    @Column
    public String seat;

    @Column
    public String motor;

    @Column(nullable = false)
    public Integer hp;

    @Column(nullable = false)
    public Integer km;

    @Column(nullable = false)
    public Integer max_velocity;

    @Column(nullable = false)
    public Integer torque;

    @Column
    public Integer fuel;

    @Column(nullable = false)
    public Integer hybrid;

    @Column(nullable = false)
    public Integer autonomy;

    @Column
    public Integer brake;

    @Column(nullable = false)
    public Integer modified;

    @Column
    public String description;

}
