package com.javiz.cars_ecommerce_backend.service;

import com.javiz.cars_ecommerce_backend.dto.CarImageDTO.CarImageRequestDTO;
import com.javiz.cars_ecommerce_backend.dto.CarImageDTO.CarImageResponseDTO;
import com.javiz.cars_ecommerce_backend.entity.CarImage;
import com.javiz.cars_ecommerce_backend.repository.CarImageRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CarImageService {

    private final CarImageRepository carImageRepository;

    @Transactional
    public List<CarImageResponseDTO> findAll() {
        return carImageRepository.findAll().stream()
                .map(this::convertToDTO)
                .toList();
    }

    @Transactional
    public CarImageResponseDTO findById(Long id) {
        return carImageRepository.findById(id)
                .map(this::convertToDTO)
                .orElseThrow(() -> new RuntimeException("CarImage not found"));
    }

    @Transactional
    public List<CarImageResponseDTO> findByCarId(Long fkidCar) {
        return carImageRepository.findByFkidCar(fkidCar).stream()
                .map(this::convertToDTO)
                .toList();
    }

    @Transactional
    public CarImageResponseDTO save(CarImageRequestDTO carImageDTO) {
        CarImage carImage = convertToEntity(carImageDTO);
        return convertToDTO(carImageRepository.save(carImage));
    }

    @Transactional
    public CarImageResponseDTO update(Long id, CarImageRequestDTO carImageDTO) {
        CarImage carImage = carImageRepository.findById(id).orElseThrow(() -> new RuntimeException("CarImage not found"));
        
        carImage.setFkidCar(carImageDTO.getFkidCar());
        carImage.setImage(carImageDTO.getImage());
        
        return convertToDTO(carImageRepository.save(carImage));
    }

    @Transactional
    public void deleteById(Long id) {
        CarImage carImage = carImageRepository.findById(id).orElseThrow(() -> new RuntimeException("CarImage not found"));
        carImageRepository.delete(carImage);
    }







    private CarImage convertToEntity(CarImageRequestDTO dto) {
        CarImage carImage = new CarImage();
        carImage.setFkidCar(dto.getFkidCar());
        carImage.setImage(dto.getImage());
        return carImage;
    }

    private CarImageResponseDTO convertToDTO(CarImage carImage) {
        CarImageResponseDTO dto = new CarImageResponseDTO();
        dto.setId(carImage.getId());
        dto.setFkidCar(carImage.getFkidCar());
        dto.setImage(carImage.getImage());
        return dto;
    }
}
