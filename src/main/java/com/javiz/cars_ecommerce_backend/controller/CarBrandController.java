package com.javiz.cars_ecommerce_backend.controller;

import com.javiz.cars_ecommerce_backend.dto.CarBrandDTO.CarBrandRequestDTO;
import com.javiz.cars_ecommerce_backend.dto.CarBrandDTO.CarBrandResponseDTO;
import com.javiz.cars_ecommerce_backend.service.CarBrandService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/JCars/api/car-brand")
public class CarBrandController {

    private final CarBrandService carBrandService;

    @GetMapping
    public ResponseEntity<List<CarBrandResponseDTO>> findAllCarBrands() {
        return ResponseEntity.status(HttpStatus.OK).body(carBrandService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<CarBrandResponseDTO> findCarBrandById(@PathVariable Long id) {
        return ResponseEntity.status(HttpStatus.OK).body(carBrandService.findById(id));
    }

    @GetMapping("/name/{name}")
    public ResponseEntity<List<CarBrandResponseDTO>> findCarBrandsByName(@PathVariable String name) {
        return ResponseEntity.status(HttpStatus.OK).body(carBrandService.findByName(name));
    }

    @PostMapping
    public ResponseEntity<CarBrandResponseDTO> saveCarBrand(@RequestBody CarBrandRequestDTO carBrand) {
        return ResponseEntity.status(HttpStatus.CREATED).body(carBrandService.save(carBrand));
    }

    @PutMapping("/{id}")
    public ResponseEntity<CarBrandResponseDTO> updateCarBrand(@PathVariable Long id, @RequestBody CarBrandRequestDTO carBrand) {
        return ResponseEntity.status(HttpStatus.OK).body(carBrandService.update(id, carBrand));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteCarBrand(@PathVariable Long id) {
        carBrandService.deleteById(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body("CarBrand deleted successfully");
    }
}
