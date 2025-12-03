package com.javiz.cars_ecommerce_backend.repository;

import com.javiz.cars_ecommerce_backend.entity.BankCard;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BankCardRepository extends JpaRepository<BankCard, Long> {
    List<BankCard> findByFkcodeBank(String fkcodeBank);
    List<BankCard> findByNumber(String number);
}
