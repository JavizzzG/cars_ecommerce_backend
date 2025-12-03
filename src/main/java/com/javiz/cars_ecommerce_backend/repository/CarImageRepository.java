package com.javiz.cars_ecommerce_backend.repository;

import com.javiz.cars_ecommerce_backend.entity.CarImage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CarImageRepository extends JpaRepository<CarImage, Long> {
    List<CarImage> findByFkidCar(Long fkidCar);
}
