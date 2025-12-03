package com.javiz.cars_ecommerce_backend.repository;

import com.javiz.cars_ecommerce_backend.entity.BankAccount;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface BankAccountRepository extends JpaRepository<BankAccount, Long> {
    List<BankAccount> findByFkcodeBank(String fkcodeBankk);
    Optional<BankAccount> findByDocument(String document);
    List<BankAccount> findByNameContainingIgnoreCase(String name);
}
