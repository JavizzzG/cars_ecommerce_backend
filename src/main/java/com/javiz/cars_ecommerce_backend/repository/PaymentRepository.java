package com.javiz.cars_ecommerce_backend.repository;

import com.javiz.cars_ecommerce_backend.entity.Payment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PaymentRepository extends JpaRepository<Payment, Long> {
    List<Payment> findByFkidOrder(String fkidOrder);
    List<Payment> findByFkcodeBank(String fkcodeBank);
    List<Payment> findByStatus(Integer status);
}
