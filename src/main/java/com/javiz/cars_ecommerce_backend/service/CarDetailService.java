package com.javiz.cars_ecommerce_backend.service;

import com.javiz.cars_ecommerce_backend.dto.CarDetailDTO.CarDetailRequestDTO;
import com.javiz.cars_ecommerce_backend.dto.CarDetailDTO.CarDetailResponseDTO;
import com.javiz.cars_ecommerce_backend.entity.CarDetail;
import com.javiz.cars_ecommerce_backend.repository.CarDetailRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CarDetailService {

    private final CarDetailRepository carDetailRepository;

    @Transactional
    public List<CarDetailResponseDTO> findAll() {
        return carDetailRepository.findAll().stream()
                .map(this::convertToDTO)
                .toList();
    }

    @Transactional
    public CarDetailResponseDTO findById(Long id) {
        return carDetailRepository.findById(id)
                .map(this::convertToDTO)
                .orElseThrow(() -> new RuntimeException("CarDetail not found"));
    }

    @Transactional
    public List<CarDetailResponseDTO> findByCarId(Long fkidCar) {
        return carDetailRepository.findByFkidCar(fkidCar).stream()
                .map(this::convertToDTO)
                .toList();
    }

    @Transactional
    public CarDetailResponseDTO save(CarDetailRequestDTO carDetailDTO) {
        CarDetail carDetail = convertToEntity(carDetailDTO);
        return convertToDTO(carDetailRepository.save(carDetail));
    }

    @Transactional
    public CarDetailResponseDTO update(Long id, CarDetailRequestDTO carDetailDTO) {
        CarDetail carDetail = carDetailRepository.findById(id).orElseThrow(() -> new RuntimeException("CarDetail not found"));
        
        carDetail.setFkidCar(carDetailDTO.getFkidCar());
        carDetail.setDoor(carDetailDTO.getDoor());
        carDetail.setSeat(carDetailDTO.getSeat());
        carDetail.setMotor(carDetailDTO.getMotor());
        carDetail.setHp(carDetailDTO.getHp());
        carDetail.setKm(carDetailDTO.getKm());
        carDetail.setMax_velocity(carDetailDTO.getMax_velocity());
        carDetail.setTorque(carDetailDTO.getTorque());
        carDetail.setFuel(carDetailDTO.getFuel());
        carDetail.setHybrid(carDetailDTO.getHybrid());
        carDetail.setAutonomy(carDetailDTO.getAutonomy());
        carDetail.setBrake(carDetailDTO.getBrake());
        carDetail.setModified(carDetailDTO.getModified());
        carDetail.setDescription(carDetailDTO.getDescription());
        
        return convertToDTO(carDetailRepository.save(carDetail));
    }

    @Transactional
    public void deleteById(Long id) {
        CarDetail carDetail = carDetailRepository.findById(id).orElseThrow(() -> new RuntimeException("CarDetail not found"));
        carDetailRepository.delete(carDetail);
    }







    private CarDetail convertToEntity(CarDetailRequestDTO dto) {
        CarDetail carDetail = new CarDetail();
        carDetail.setFkidCar(dto.getFkidCar());
        carDetail.setDoor(dto.getDoor());
        carDetail.setSeat(dto.getSeat());
        carDetail.setMotor(dto.getMotor());
        carDetail.setHp(dto.getHp());
        carDetail.setKm(dto.getKm());
        carDetail.setMax_velocity(dto.getMax_velocity());
        carDetail.setTorque(dto.getTorque());
        carDetail.setFuel(dto.getFuel());
        carDetail.setHybrid(dto.getHybrid());
        carDetail.setAutonomy(dto.getAutonomy());
        carDetail.setBrake(dto.getBrake());
        carDetail.setModified(dto.getModified());
        carDetail.setDescription(dto.getDescription());
        return carDetail;
    }

    private CarDetailResponseDTO convertToDTO(CarDetail carDetail) {
        CarDetailResponseDTO dto = new CarDetailResponseDTO();
        dto.setId(carDetail.getId());
        dto.setFkidCar(carDetail.getFkidCar());
        dto.setDoor(carDetail.getDoor());
        dto.setSeat(carDetail.getSeat());
        dto.setMotor(carDetail.getMotor());
        dto.setHp(carDetail.getHp());
        dto.setKm(carDetail.getKm());
        dto.setMax_velocity(carDetail.getMax_velocity());
        dto.setTorque(carDetail.getTorque());
        dto.setFuel(carDetail.getFuel());
        dto.setHybrid(carDetail.getHybrid());
        dto.setAutonomy(carDetail.getAutonomy());
        dto.setBrake(carDetail.getBrake());
        dto.setModified(carDetail.getModified());
        dto.setDescription(carDetail.getDescription());
        return dto;
    }
}
