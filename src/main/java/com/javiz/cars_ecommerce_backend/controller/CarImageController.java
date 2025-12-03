package com.javiz.cars_ecommerce_backend.controller;

import com.javiz.cars_ecommerce_backend.dto.CarImageDTO.CarImageRequestDTO;
import com.javiz.cars_ecommerce_backend.dto.CarImageDTO.CarImageResponseDTO;
import com.javiz.cars_ecommerce_backend.service.CarImageService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/JCars/api/car-image")
public class CarImageController {

    private final CarImageService carImageService;

    @GetMapping
    public ResponseEntity<List<CarImageResponseDTO>> findAllCarImages() {
        return ResponseEntity.status(HttpStatus.OK).body(carImageService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<CarImageResponseDTO> findCarImageById(@PathVariable Long id) {
        return ResponseEntity.status(HttpStatus.OK).body(carImageService.findById(id));
    }

    @GetMapping("/car/{carId}")
    public ResponseEntity<List<CarImageResponseDTO>> findCarImagesByCarId(@PathVariable Long carId) {
        return ResponseEntity.status(HttpStatus.OK).body(carImageService.findByCarId(carId));
    }

    @PostMapping
    public ResponseEntity<CarImageResponseDTO> saveCarImage(@RequestBody CarImageRequestDTO carImage) {
        return ResponseEntity.status(HttpStatus.CREATED).body(carImageService.save(carImage));
    }

    @PutMapping("/{id}")
    public ResponseEntity<CarImageResponseDTO> updateCarImage(@PathVariable Long id, @RequestBody CarImageRequestDTO carImage) {
        return ResponseEntity.status(HttpStatus.OK).body(carImageService.update(id, carImage));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteCarImage(@PathVariable Long id) {
        carImageService.deleteById(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body("CarImage deleted successfully");
    }
}
