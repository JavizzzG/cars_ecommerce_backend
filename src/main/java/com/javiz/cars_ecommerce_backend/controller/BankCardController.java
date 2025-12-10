package com.javiz.cars_ecommerce_backend.controller;

import com.javiz.cars_ecommerce_backend.dto.BankCardDTO.BankCardRequestDTO;
import com.javiz.cars_ecommerce_backend.dto.BankCardDTO.BankCardResponseDTO;
import com.javiz.cars_ecommerce_backend.service.BankCardService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/JCars/api/bank-card")
public class BankCardController {

    private final BankCardService bankCardService;

    @GetMapping
    public ResponseEntity<List<BankCardResponseDTO>> findAllBankCards() {
        return ResponseEntity.status(HttpStatus.OK).body(bankCardService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<BankCardResponseDTO> findBankCardById(@PathVariable Long id) {
        return ResponseEntity.status(HttpStatus.OK).body(bankCardService.findById(id));
    }

    @GetMapping("/bank/{bankCode}")
    public ResponseEntity<List<BankCardResponseDTO>> findBankCardsByBankCode(@PathVariable String bankCode) {
        return ResponseEntity.status(HttpStatus.OK).body(bankCardService.findByBankCode(bankCode));
    }

    @GetMapping("/number/{number}")
    public ResponseEntity<BankCardResponseDTO> findBankCardsByNumber(@PathVariable String number) {
        return ResponseEntity.status(HttpStatus.OK).body(bankCardService.findByCardNumber(number));
    }

    @PostMapping
    public ResponseEntity<BankCardResponseDTO> createBankCard(@RequestBody BankCardRequestDTO bankCard) {
        return ResponseEntity.status(HttpStatus.CREATED).body(bankCardService.save(bankCard));
    }

    @PutMapping("/{id}")
    public ResponseEntity<BankCardResponseDTO> updateBankCard(
            @PathVariable Long id,
            @RequestBody BankCardRequestDTO bankCard) {
        return ResponseEntity.status(HttpStatus.OK).body(bankCardService.update(id, bankCard));
    }

    @PutMapping("/{id}/balance")
    public ResponseEntity<BankCardResponseDTO> updateBankCardBalance(@PathVariable Long id, @RequestParam BigDecimal balance) {
        return ResponseEntity.status(HttpStatus.OK).body(bankCardService.updateBalance(id, balance));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteBankCard(@PathVariable Long id) {
        bankCardService.deleteById(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body("Bank card deleted successfully");
    }
}
