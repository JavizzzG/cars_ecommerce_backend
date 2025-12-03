package com.javiz.cars_ecommerce_backend.controller;

import com.javiz.cars_ecommerce_backend.dto.BankAccountDTO.BankAccountRequestDTO;
import com.javiz.cars_ecommerce_backend.dto.BankAccountDTO.BankAccountResponseDTO;
import com.javiz.cars_ecommerce_backend.service.BankAccountService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/JCars/api/bank-account")
public class BankAccountController {

    private final BankAccountService bankAccountService;

    @GetMapping
    public ResponseEntity<List<BankAccountResponseDTO>> findAllBankAccounts() {
        return ResponseEntity.status(HttpStatus.OK).body(bankAccountService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<BankAccountResponseDTO> findBankAccountById(@PathVariable Long id) {
        return ResponseEntity.status(HttpStatus.OK).body(bankAccountService.findById(id));
    }

    @GetMapping("/bank/{bankCode}")
    public ResponseEntity<List<BankAccountResponseDTO>> findBankAccountsByBankCode(@PathVariable String bankCode) {
        return ResponseEntity.status(HttpStatus.OK).body(bankAccountService.findByBankCode(bankCode));
    }

    @GetMapping("/document/{document}")
    public ResponseEntity<BankAccountResponseDTO> findBankAccountByDocument(@PathVariable String document) {
        return ResponseEntity.status(HttpStatus.OK).body(bankAccountService.findByDocument(document));
    }

    @GetMapping("/name/{name}")
    public ResponseEntity<List<BankAccountResponseDTO>> findBankAccountsByName(@PathVariable String name) {
        return ResponseEntity.status(HttpStatus.OK).body(bankAccountService.findByName(name));
    }

    @PostMapping
    public ResponseEntity<BankAccountResponseDTO> createBankAccount(@RequestBody BankAccountRequestDTO bankAccount) {
        return ResponseEntity.status(HttpStatus.CREATED).body(bankAccountService.save(bankAccount));
    }

    @PutMapping("/{id}")
    public ResponseEntity<BankAccountResponseDTO> updateBankAccount(@PathVariable Long id, @RequestBody BankAccountRequestDTO bankAccount) {
        return ResponseEntity.status(HttpStatus.OK).body(bankAccountService.update(id, bankAccount));
    }

    @PutMapping("/{id}/balance")
    public ResponseEntity<BankAccountResponseDTO> updateBankAccountBalance(@PathVariable Long id, @RequestParam BigDecimal amount) {
        return ResponseEntity.status(HttpStatus.OK).body(bankAccountService.updateBalance(id, amount));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteBankAccount(@PathVariable Long id) {
        bankAccountService.deleteById(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body("Bank account deleted successfully");
    }
}
