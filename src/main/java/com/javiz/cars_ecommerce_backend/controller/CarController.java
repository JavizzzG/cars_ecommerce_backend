package com.javiz.cars_ecommerce_backend.controller;

import com.javiz.cars_ecommerce_backend.dto.CarDTO.CarRequestDTO;
import com.javiz.cars_ecommerce_backend.dto.CarDTO.CarResponseDTO;
import com.javiz.cars_ecommerce_backend.service.CarService;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.autoconfigure.graphql.GraphQlProperties;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/JCars/api/car")
public class CarController {

    private final CarService carService;

    @GetMapping
    public ResponseEntity<List<CarResponseDTO>> getAllCars() {
        return ResponseEntity.ok(carService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<CarResponseDTO> findCarById(@PathVariable Long id) {
        return ResponseEntity.status(HttpStatus.OK).body(carService.findById(id));
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<List<CarResponseDTO>> getCarsByUser(@PathVariable Long userId) {
        return ResponseEntity.status(HttpStatus.OK).body(carService.findByUserId(userId));
    }

    @GetMapping("/name/{name}")
    public ResponseEntity<List<CarResponseDTO>> getCarsByName(@PathVariable String name) {
        return ResponseEntity.status(HttpStatus.OK).body(carService.findByName(name));
    }

    @PostMapping
    public ResponseEntity<CarResponseDTO> saveCar(@RequestBody CarRequestDTO car) {
        return ResponseEntity.status(HttpStatus.CREATED).body(carService.save(car));
    }

    @PutMapping("/{id}")
    public ResponseEntity<CarResponseDTO> updateCar(@PathVariable Long id, @RequestBody CarRequestDTO car) {
        return ResponseEntity.status(HttpStatus.OK).body(carService.update(id, car));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteCar(@PathVariable Long id) {
        carService.deleteById(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body("User deleted successfully");
    }
}
