package com.javiz.cars_ecommerce_backend.service;

import com.javiz.cars_ecommerce_backend.dto.BankCardDTO.BankCardRequestDTO;
import com.javiz.cars_ecommerce_backend.dto.BankCardDTO.BankCardResponseDTO;
import com.javiz.cars_ecommerce_backend.entity.BankCard;
import com.javiz.cars_ecommerce_backend.repository.BankCardRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
@RequiredArgsConstructor
public class BankCardService {

    private final BankCardRepository bankCardRepository;

    @Transactional
    public List<BankCardResponseDTO> findAll() {
        return bankCardRepository.findAll().stream()
                .map(this::convertToDTO)
                .toList();
    }

    @Transactional
    public BankCardResponseDTO findById(Long id) {
        return bankCardRepository.findById(id)
                .map(this::convertToDTO)
                .orElseThrow(() -> new RuntimeException("Bank card not found"));
    }

    @Transactional
    public List<BankCardResponseDTO> findByBankCode(String bankCode) {
        return bankCardRepository.findByFkcodeBank(bankCode).stream()
                .map(this::convertToDTO)
                .toList();
    }

    @Transactional
    public BankCardResponseDTO findByCardNumber(String number) {
        return bankCardRepository.findByNumber(number).stream()
                .map(this::convertToDTO)
                .findFirst()
                .orElseThrow(() -> new RuntimeException("Bank card not found"));
    }

    @Transactional
    public BankCardResponseDTO save(BankCardRequestDTO bankCardDTO) {
        BankCard bankCard = convertToEntity(bankCardDTO);
        return convertToDTO(bankCardRepository.save(bankCard));
    }

    @Transactional
    public BankCardResponseDTO update(Long id, BankCardRequestDTO bankCardDTO) {
        BankCard bankCard = bankCardRepository.findById(id).orElseThrow(() -> new RuntimeException("Bank card not found"));
        
        bankCard.setFkcodeBank(bankCardDTO.getFkcodeBank());
        bankCard.setNumber(bankCardDTO.getNumber());
        bankCard.setCvc(bankCardDTO.getCvc());
        bankCard.setName(bankCardDTO.getName());
        bankCard.setExpire_date(bankCardDTO.getExpire_date());
        bankCard.setBalance(bankCardDTO.getBalance());
        bankCard.setStatus(bankCardDTO.getStatus());
        
        return convertToDTO(bankCardRepository.save(bankCard));
    }

    @Transactional
    public void deleteById(Long id) {
        BankCard bankCard = bankCardRepository.findById(id).orElseThrow(() -> new RuntimeException("Bank card not found"));
        bankCardRepository.delete(bankCard);
    }

    @Transactional
    public BankCardResponseDTO updateBalance(Long id, BigDecimal price) {
        BankCard bankCard = bankCardRepository.findById(id).orElseThrow(() -> new RuntimeException("Bank card not found"));
        bankCard.setBalance(bankCard.getBalance().subtract(price));
        return convertToDTO(bankCardRepository.save(bankCard));
    }












    private BankCard convertToEntity(BankCardRequestDTO dto) {
        BankCard bankCard = new BankCard();
        bankCard.setFkcodeBank(dto.getFkcodeBank());
        bankCard.setNumber(dto.getNumber());
        bankCard.setCvc(dto.getCvc());
        bankCard.setName(dto.getName());
        bankCard.setExpire_date(dto.getExpire_date());
        bankCard.setBalance(dto.getBalance() != null ? dto.getBalance() : BigDecimal.ZERO);
        bankCard.setStatus(dto.getStatus() != null ? dto.getStatus() : 1); // Default status: 1 (active)
        return bankCard;
    }

    private BankCardResponseDTO convertToDTO(BankCard bankCard) {
        BankCardResponseDTO dto = new BankCardResponseDTO();
        dto.setId(bankCard.getId());
        dto.setFkcodeBank(bankCard.getFkcodeBank());
        dto.setNumber(bankCard.getNumber());
        dto.setCvc(bankCard.getCvc());
        dto.setName(bankCard.getName());
        dto.setExpire_date(bankCard.getExpire_date());
        dto.setBalance(bankCard.getBalance());
        dto.setStatus(bankCard.getStatus());
        return dto;
    }
}
