package com.javiz.cars_ecommerce_backend.service;

import com.javiz.cars_ecommerce_backend.dto.BankDTO.BankRequestDTO;
import com.javiz.cars_ecommerce_backend.dto.BankDTO.BankResponseDTO;
import com.javiz.cars_ecommerce_backend.entity.Bank;
import com.javiz.cars_ecommerce_backend.repository.BankRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BankService {

    private final BankRepository bankRepository;

    @Transactional
    public List<BankResponseDTO> findAll() {
        return bankRepository.findAll().stream()
                .map(this::convertToDTO)
                .toList();
    }

    @Transactional
    public BankResponseDTO findById(Long id) {
        return bankRepository.findById(id)
                .map(this::convertToDTO)
                .orElseThrow(() -> new RuntimeException("Bank not found"));
    }

    @Transactional
    public List<BankResponseDTO> findByName(String name) {
        return bankRepository.findByNameContainingIgnoreCase(name).stream()
                .map(this::convertToDTO)
                .toList();
    }

    @Transactional
    public List<BankResponseDTO> findByCode(String code) {
        return bankRepository.findByCode(code).stream()
                .map(this::convertToDTO)
                .toList();
    }

    @Transactional
    public BankResponseDTO save(BankRequestDTO bankDTO) {
        Bank bank = convertToEntity(bankDTO);
        return convertToDTO(bankRepository.save(bank));
    }

    @Transactional
    public BankResponseDTO update(Long id, BankRequestDTO bankDTO) {
        Bank bank = bankRepository.findById(id).orElseThrow(() -> new RuntimeException("Bank not found"));
        
        bank.setName(bankDTO.getName());
        bank.setCode(bankDTO.getCode());
        
        return convertToDTO(bankRepository.save(bank));
    }

    @Transactional
    public void deleteById(Long id) {
        Bank bank = bankRepository.findById(id).orElseThrow(() -> new RuntimeException("Bank not found"));
        bankRepository.delete(bank);
    }









    private Bank convertToEntity(BankRequestDTO dto) {
        Bank bank = new Bank();
        bank.setName(dto.getName());
        bank.setCode(dto.getCode());
        return bank;
    }

    private BankResponseDTO convertToDTO(Bank bank) {
        BankResponseDTO dto = new BankResponseDTO();
        dto.setId(bank.getId());
        dto.setName(bank.getName());
        dto.setCode(bank.getCode());
        return dto;
    }
}
