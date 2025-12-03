package com.javiz.cars_ecommerce_backend.service;

import com.javiz.cars_ecommerce_backend.dto.CarDTO.CarRequestDTO;
import com.javiz.cars_ecommerce_backend.dto.CarDTO.CarResponseDTO;
import com.javiz.cars_ecommerce_backend.entity.Car;
import com.javiz.cars_ecommerce_backend.entity.User;
import com.javiz.cars_ecommerce_backend.repository.CarRepository;
import com.javiz.cars_ecommerce_backend.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CarService {

    private final CarRepository carRepository;

    @Transactional
    public List<CarResponseDTO> findAll() {
        return carRepository.findAll().stream()
                .map(this::convertToDTO)
                .toList();
    }

    @Transactional
    public CarResponseDTO findById(Long id) {
        return carRepository.findById(id)
                .map(this::convertToDTO)
                .orElseThrow(() -> new RuntimeException("Car not found"));
    }

    @Transactional
    public List<CarResponseDTO> findByUserId(Long fkidUser) {
        return carRepository.findByFkidUser(fkidUser).stream()
                .map(this::convertToDTO)
                .toList();
    }

    @Transactional
    public List<CarResponseDTO> findByName(String name) {
        return carRepository.findByName(name).stream()
                .map(this::convertToDTO)
                .toList();
    }

    @Transactional
    public CarResponseDTO save(CarRequestDTO car) {
        return convertToDTO(carRepository.save(convertToEntity(car)));
    }

    @Transactional
    public CarResponseDTO update(Long id, CarRequestDTO carDTO) {
        Car car = carRepository.findById(id).orElseThrow(() -> new RuntimeException("Car not found"));

        car.setName(carDTO.getName());
        car.setModel(carDTO.getModel());
        car.setYear(carDTO.getYear());
        car.setFkidBrand(carDTO.getFkidBrand());
        car.setPrice(carDTO.getPrice());
        car.setFor_sale(carDTO.getFor_sale());
        car.setFkidUser(carDTO.getFkidUser());

        return convertToDTO(carRepository.save(car));
    }

    @Transactional
    public void deleteById(Long id) {
        Car car = carRepository.findById(id).orElseThrow(() -> new RuntimeException("Car not found"));
        carRepository.delete(car);
    }





    private Car convertToEntity(CarRequestDTO carDTO){
        Car car = new Car();
        car.setName(carDTO.getName());
        car.setModel(carDTO.getModel());
        car.setYear(carDTO.getYear());
        car.setFkidBrand(carDTO.getFkidBrand());
        car.setPrice(carDTO.getPrice());
        car.setFor_sale(carDTO.getFor_sale());
        car.setFkidUser(carDTO.getFkidUser());
        return car;
    }

    private CarResponseDTO convertToDTO(Car car){
        CarResponseDTO carDTO = new CarResponseDTO();
        carDTO.setId(car.getId());
        carDTO.setName(car.getName());
        carDTO.setModel(car.getModel());
        carDTO.setYear(car.getYear());
        carDTO.setFkidBrand(car.getFkidBrand());
        carDTO.setPrice(car.getPrice());
        carDTO.setFor_sale(car.getFor_sale());
        carDTO.setFkidUser(car.getFkidUser());
        return carDTO;
    }
}
