package com.javiz.cars_ecommerce_backend.service;

import com.javiz.cars_ecommerce_backend.dto.PaymentDTO.PaymentRequestDTO;
import com.javiz.cars_ecommerce_backend.dto.PaymentDTO.PaymentResponseDTO;
import com.javiz.cars_ecommerce_backend.entity.Payment;
import com.javiz.cars_ecommerce_backend.repository.PaymentRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class PaymentService {

    private final PaymentRepository paymentRepository;

    @Transactional
    public List<PaymentResponseDTO> findAll() {
        return paymentRepository.findAll().stream()
                .map(this::convertToDTO)
                .toList();
    }

    @Transactional
    public PaymentResponseDTO findById(Long id) {
        return paymentRepository.findById(id)
                .map(this::convertToDTO)
                .orElseThrow(() -> new RuntimeException("Payment not found"));
    }

    @Transactional
    public List<PaymentResponseDTO> findByOrderId(String orderId) {
        return paymentRepository.findByFkidOrder(orderId).stream()
                .map(this::convertToDTO)
                .toList();
    }

    @Transactional
    public List<PaymentResponseDTO> findByBankCode(String bankCode) {
        return paymentRepository.findByFkcodeBank(bankCode).stream()
                .map(this::convertToDTO)
                .toList();
    }

    @Transactional
    public List<PaymentResponseDTO> findByStatus(Integer status) {
        return paymentRepository.findByStatus(status).stream()
                .map(this::convertToDTO)
                .toList();
    }

    @Transactional
    public PaymentResponseDTO save(PaymentRequestDTO paymentDTO) {
        Payment payment = convertToEntity(paymentDTO);
        payment.setCreated_at(LocalDateTime.now());
        payment.setUpdated_at(LocalDateTime.now());
        return convertToDTO(paymentRepository.save(payment));
    }

    @Transactional
    public PaymentResponseDTO update(Long id, PaymentRequestDTO paymentDTO) {
        Payment payment = paymentRepository.findById(id).orElseThrow(() -> new RuntimeException("Payment not found"));
        
        payment.setFkidOrder(paymentDTO.getFkidOrder());
        payment.setAmount(paymentDTO.getAmount());
        payment.setFkcodeBank(paymentDTO.getFkcodeBank());
        payment.setStatus(paymentDTO.getStatus());
        payment.setUpdated_at(LocalDateTime.now());
        
        return convertToDTO(paymentRepository.save(payment));
    }

    @Transactional
    public void deleteById(Long id) {
        Payment payment = paymentRepository.findById(id).orElseThrow(() -> new RuntimeException("Payment not found"));
        paymentRepository.delete(payment);
    }

    @Transactional
    public PaymentResponseDTO updateStatus(Long id, Integer status) {
        Payment payment = paymentRepository.findById(id).orElseThrow(() -> new RuntimeException("Payment not found"));
        payment.setStatus(status);
        payment.setUpdated_at(LocalDateTime.now());
        return convertToDTO(paymentRepository.save(payment));
    }












    private Payment convertToEntity(PaymentRequestDTO dto) {
        Payment payment = new Payment();
        payment.setFkidOrder(dto.getFkidOrder());
        payment.setAmount(dto.getAmount());
        payment.setFkcodeBank(dto.getFkcodeBank());
        payment.setStatus(dto.getStatus() != null ? dto.getStatus() : 0); // Default status: 0 (pending)
        payment.setCreated_at(dto.getCreated_at() != null ? dto.getCreated_at() : LocalDateTime.now());
        payment.setUpdated_at(dto.getUpdated_at() != null ? dto.getUpdated_at() : LocalDateTime.now());
        return payment;
    }

    private PaymentResponseDTO convertToDTO(Payment payment) {
        PaymentResponseDTO dto = new PaymentResponseDTO();
        dto.setId(payment.getId());
        dto.setFkidOrder(payment.getFkidOrder());
        dto.setAmount(payment.getAmount());
        dto.setFkcodeBank(payment.getFkcodeBank());
        dto.setStatus(payment.getStatus());
        dto.setCreated_at(payment.getCreated_at());
        dto.setUpdated_at(payment.getUpdated_at());
        return dto;
    }
}
