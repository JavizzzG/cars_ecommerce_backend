package com.javiz.cars_ecommerce_backend.repository;

import com.javiz.cars_ecommerce_backend.entity.CarDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CarDetailRepository extends JpaRepository<CarDetail, Long> {
    List<CarDetail> findByFkidCar(Long fkidCar);
}
