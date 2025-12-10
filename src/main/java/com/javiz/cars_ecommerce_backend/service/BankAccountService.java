package com.javiz.cars_ecommerce_backend.service;

import com.javiz.cars_ecommerce_backend.dto.BankAccountDTO.BankAccountRequestDTO;
import com.javiz.cars_ecommerce_backend.dto.BankAccountDTO.BankAccountResponseDTO;
import com.javiz.cars_ecommerce_backend.entity.BankAccount;
import com.javiz.cars_ecommerce_backend.repository.BankAccountRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
@RequiredArgsConstructor
public class BankAccountService {

    private final BankAccountRepository bankAccountRepository;

    @Transactional
    public List<BankAccountResponseDTO> findAll() {
        return bankAccountRepository.findAll().stream()
                .map(this::convertToDTO)
                .toList();
    }

    @Transactional
    public BankAccountResponseDTO findById(Long id) {
        return bankAccountRepository.findById(id)
                .map(this::convertToDTO)
                .orElseThrow(() -> new RuntimeException("Bank account not found"));
    }

    @Transactional
    public List<BankAccountResponseDTO> findByBankCode(String fkcodeBank) {
        return bankAccountRepository.findByFkcodeBank(fkcodeBank).stream()
                .map(this::convertToDTO)
                .toList();
    }

    @Transactional
    public BankAccountResponseDTO findByDocument(String document) {
        return bankAccountRepository.findByDocument(document)
                .map(this::convertToDTO)
                .orElseThrow(() -> new RuntimeException("Bank account not found with document: " + document));
    }

    @Transactional
    public List<BankAccountResponseDTO> findByName(String name) {
        return bankAccountRepository.findByNameContainingIgnoreCase(name).stream()
                .map(this::convertToDTO)
                .toList();
    }

    @Transactional
    public BankAccountResponseDTO save(BankAccountRequestDTO bankAccountDTO) {
        if (bankAccountRepository.findByDocument(bankAccountDTO.getDocument()).isPresent()) {
            throw new RuntimeException("Bank account with this document already exists");
        }
        
        BankAccount bankAccount = convertToEntity(bankAccountDTO);
        return convertToDTO(bankAccountRepository.save(bankAccount));
    }

    @Transactional
    public BankAccountResponseDTO update(Long id, BankAccountRequestDTO bankAccountDTO) {
        BankAccount bankAccount = bankAccountRepository.findById(id).orElseThrow(() -> new RuntimeException("Bank account not found"));
        
        // Check if document is being changed and if it's already in use
        if (!bankAccount.getDocument().equals(bankAccountDTO.getDocument()) && 
            bankAccountRepository.findByDocument(bankAccountDTO.getDocument()).isPresent()) {
            throw new RuntimeException("Another bank account with this document already exists");
        }
        
        bankAccount.setFkcodeBank(bankAccountDTO.getFkcodeBank());
        bankAccount.setDocument(bankAccountDTO.getDocument());
        bankAccount.setName(bankAccountDTO.getName());
        bankAccount.setPassword(bankAccountDTO.getPassword());
        bankAccount.setBalance(bankAccountDTO.getBalance());
        bankAccount.setStatus(bankAccountDTO.getStatus());
        
        return convertToDTO(bankAccountRepository.save(bankAccount));
    }

    @Transactional
    public void deleteById(Long id) {
        BankAccount bankAccount = bankAccountRepository.findById(id).orElseThrow(() -> new RuntimeException("Bank account not found"));
        bankAccountRepository.delete(bankAccount);
    }

    @Transactional
    public BankAccountResponseDTO updateBalance(Long id, BigDecimal amount) {
        BankAccount bankAccount = bankAccountRepository.findById(id).orElseThrow(() -> new RuntimeException("Bank account not found"));
        
        bankAccount.setBalance(bankAccount.getBalance().subtract(amount));
        return convertToDTO(bankAccountRepository.save(bankAccount));
    }









    private BankAccount convertToEntity(BankAccountRequestDTO dto) {
        BankAccount bankAccount = new BankAccount();
        bankAccount.setFkcodeBank(dto.getFkcodeBank());
        bankAccount.setDocument(dto.getDocument());
        bankAccount.setName(dto.getName());
        bankAccount.setPassword(dto.getPassword());
        bankAccount.setBalance(dto.getBalance() != null ? dto.getBalance() : BigDecimal.ZERO);
        bankAccount.setStatus(dto.getStatus() != null ? dto.getStatus() : 1); // Default status: 1 (active)
        return bankAccount;
    }

    private BankAccountResponseDTO convertToDTO(BankAccount bankAccount) {
        BankAccountResponseDTO dto = new BankAccountResponseDTO();
        dto.setId(bankAccount.getId());
        dto.setFkcodeBank(bankAccount.getFkcodeBank());
        dto.setDocument(bankAccount.getDocument());
        dto.setName(bankAccount.getName());
        dto.setBalance(bankAccount.getBalance());
        dto.setStatus(bankAccount.getStatus());
        dto.setPassword(bankAccount.getPassword());
        return dto;
    }
}
