package com.javiz.cars_ecommerce_backend.controller;

import com.javiz.cars_ecommerce_backend.dto.BankDTO.BankRequestDTO;
import com.javiz.cars_ecommerce_backend.dto.BankDTO.BankResponseDTO;
import com.javiz.cars_ecommerce_backend.service.BankService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/JCars/api/bank")
public class BankController {

    private final BankService bankService;

    @GetMapping
    public ResponseEntity<List<BankResponseDTO>> findAllBanks() {
        return ResponseEntity.status(HttpStatus.OK).body(bankService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<BankResponseDTO> findBankById(@PathVariable Long id) {
        return ResponseEntity.status(HttpStatus.OK).body(bankService.findById(id));
    }

    @GetMapping("/name/{name}")
    public ResponseEntity<List<BankResponseDTO>> findBanksByName(@PathVariable String name) {
        return ResponseEntity.status(HttpStatus.OK).body(bankService.findByName(name));
    }

    @GetMapping("/code/{code}")
    public ResponseEntity<List<BankResponseDTO>> findBanksByCode(@PathVariable String code) {
        return ResponseEntity.status(HttpStatus.OK).body(bankService.findByCode(code));
    }

    @PostMapping
    public ResponseEntity<BankResponseDTO> createBank(@RequestBody BankRequestDTO bank) {
        return ResponseEntity.status(HttpStatus.CREATED).body(bankService.save(bank));
    }

    @PutMapping("/{id}")
    public ResponseEntity<BankResponseDTO> updateBank(@PathVariable Long id, @RequestBody BankRequestDTO bank) {
        return ResponseEntity.status(HttpStatus.OK).body(bankService.update(id, bank));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteBank(@PathVariable Long id) {
        bankService.deleteById(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body("Bank deleted successfully");
    }
}
