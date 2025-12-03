package com.javiz.cars_ecommerce_backend.service;

import com.javiz.cars_ecommerce_backend.dto.CarBrandDTO.CarBrandRequestDTO;
import com.javiz.cars_ecommerce_backend.dto.CarBrandDTO.CarBrandResponseDTO;
import com.javiz.cars_ecommerce_backend.entity.CarBrand;
import com.javiz.cars_ecommerce_backend.repository.CarBrandRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CarBrandService {

    private final CarBrandRepository carBrandRepository;

    @Transactional
    public List<CarBrandResponseDTO> findAll() {
        return carBrandRepository.findAll().stream()
                .map(this::convertToDTO)
                .toList();
    }

    @Transactional
    public CarBrandResponseDTO findById(Long id) {
        return carBrandRepository.findById(id)
                .map(this::convertToDTO)
                .orElseThrow(() -> new RuntimeException("CarBrand not found"));
    }

    @Transactional
    public List<CarBrandResponseDTO> findByName(String name) {
        return carBrandRepository.findByName(name).stream()
                .map(this::convertToDTO)
                .toList();
    }

    @Transactional
    public CarBrandResponseDTO save(CarBrandRequestDTO carBrandDTO) {
        CarBrand carBrand = convertToEntity(carBrandDTO);
        return convertToDTO(carBrandRepository.save(carBrand));
    }

    @Transactional
    public CarBrandResponseDTO update(Long id, CarBrandRequestDTO carBrandDTO) {
        CarBrand carBrand = carBrandRepository.findById(id).orElseThrow(() -> new RuntimeException("CarBrand not found"));
        
        carBrand.setName(carBrandDTO.getName());
        
        return convertToDTO(carBrandRepository.save(carBrand));
    }

    @Transactional
    public void deleteById(Long id) {
        CarBrand carBrand = carBrandRepository.findById(id).orElseThrow(() -> new RuntimeException("CarBrand not found"));
        carBrandRepository.delete(carBrand);
    }





    private CarBrand convertToEntity(CarBrandRequestDTO dto) {
        CarBrand carBrand = new CarBrand();
        carBrand.setName(dto.getName());
        return carBrand;
    }

    private CarBrandResponseDTO convertToDTO(CarBrand carBrand) {
        CarBrandResponseDTO dto = new CarBrandResponseDTO();
        dto.setId(carBrand.getId());
        dto.setName(carBrand.getName());
        return dto;
    }
}
