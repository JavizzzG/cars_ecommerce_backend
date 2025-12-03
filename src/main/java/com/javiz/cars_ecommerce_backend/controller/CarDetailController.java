package com.javiz.cars_ecommerce_backend.controller;

import com.javiz.cars_ecommerce_backend.dto.CarDetailDTO.CarDetailRequestDTO;
import com.javiz.cars_ecommerce_backend.dto.CarDetailDTO.CarDetailResponseDTO;
import com.javiz.cars_ecommerce_backend.service.CarDetailService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/JCars/api/car-detail")
public class CarDetailController {

    private final CarDetailService carDetailService;

    @GetMapping
    public ResponseEntity<List<CarDetailResponseDTO>> findAllCarDetails() {
        return ResponseEntity.status(HttpStatus.OK).body(carDetailService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<CarDetailResponseDTO> findCarDetailById(@PathVariable Long id) {
        return ResponseEntity.status(HttpStatus.OK).body(carDetailService.findById(id));
    }

    @GetMapping("/car/{carId}")
    public ResponseEntity<List<CarDetailResponseDTO>> findCarDetailsByCarId(@PathVariable Long carId) {
        return ResponseEntity.status(HttpStatus.OK).body(carDetailService.findByCarId(carId));
    }

    @PostMapping
    public ResponseEntity<CarDetailResponseDTO> saveCarDetail(@RequestBody CarDetailRequestDTO carDetail) {
        return ResponseEntity.status(HttpStatus.CREATED).body(carDetailService.save(carDetail));
    }

    @PutMapping("/{id}")
    public ResponseEntity<CarDetailResponseDTO> updateCarDetail(@PathVariable Long id, @RequestBody CarDetailRequestDTO carDetail) {
        return ResponseEntity.status(HttpStatus.OK).body(carDetailService.update(id, carDetail));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteCarDetail(@PathVariable Long id) {
        carDetailService.deleteById(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body("CarDetail deleted successfully");
    }
}
