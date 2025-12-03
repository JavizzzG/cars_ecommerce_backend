package com.javiz.cars_ecommerce_backend.controller;

import com.javiz.cars_ecommerce_backend.dto.PaymentDTO.PaymentRequestDTO;
import com.javiz.cars_ecommerce_backend.dto.PaymentDTO.PaymentResponseDTO;
import com.javiz.cars_ecommerce_backend.service.PaymentService;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/JCars/api/payment")
public class PaymentController {

    private final PaymentService paymentService;

    @GetMapping
    public ResponseEntity<List<PaymentResponseDTO>> findAllPayments() {
        return ResponseEntity.status(HttpStatus.OK).body(paymentService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<PaymentResponseDTO> findPaymentById(@PathVariable Long id) {
        return ResponseEntity.status(HttpStatus.OK).body(paymentService.findById(id));
    }

    @GetMapping("/order/{orderId}")
    public ResponseEntity<List<PaymentResponseDTO>> findPaymentsByOrderId(@PathVariable String orderId) {
        return ResponseEntity.status(HttpStatus.OK).body(paymentService.findByOrderId(orderId));
    }

    @GetMapping("/bank/{bankCode}")
    public ResponseEntity<List<PaymentResponseDTO>> findPaymentsByBankCode(@PathVariable String bankCode) {
        return ResponseEntity.status(HttpStatus.OK).body(paymentService.findByBankCode(bankCode));
    }

    @GetMapping("/status/{status}")
    public ResponseEntity<List<PaymentResponseDTO>> findPaymentsByStatus(@PathVariable Integer status) {
        return ResponseEntity.status(HttpStatus.OK).body(paymentService.findByStatus(status));
    }

    @PostMapping
    public ResponseEntity<PaymentResponseDTO> createPayment(@RequestBody PaymentRequestDTO payment) {
        return ResponseEntity.status(HttpStatus.CREATED).body(paymentService.save(payment));
    }

    @PutMapping("/{id}")
    public ResponseEntity<PaymentResponseDTO> updatePayment(@PathVariable Long id, @RequestBody PaymentRequestDTO payment) {
        return ResponseEntity.status(HttpStatus.OK).body(paymentService.update(id, payment));
    }

    @PutMapping("/{id}/status")
    public ResponseEntity<PaymentResponseDTO> updatePaymentStatus(@PathVariable Long id, @RequestParam Integer status) {
        return ResponseEntity.status(HttpStatus.OK).body(paymentService.updateStatus(id, status));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deletePayment(@PathVariable Long id) {
        paymentService.deleteById(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body("Payment deleted successfully");
    }
}
