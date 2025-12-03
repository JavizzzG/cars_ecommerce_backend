package com.javiz.cars_ecommerce_backend.repository;

import com.javiz.cars_ecommerce_backend.entity.Bank;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BankRepository extends JpaRepository<Bank, Long> {
    List<Bank> findByNameContainingIgnoreCase(String name);
    List<Bank> findByCode(String code);
}
