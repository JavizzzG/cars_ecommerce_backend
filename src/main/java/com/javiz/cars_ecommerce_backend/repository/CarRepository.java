package com.javiz.cars_ecommerce_backend.repository;

import com.javiz.cars_ecommerce_backend.entity.Car;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CarRepository extends JpaRepository<Car, Long> {
    List<Car> findByFkidUser(Long fkidUser);
    List<Car> findByName(String name);
}
