package com.javiz.cars_ecommerce_backend.controller;

import com.javiz.cars_ecommerce_backend.dto.OrderDTO.OrderRequestDTO;
import com.javiz.cars_ecommerce_backend.dto.OrderDTO.OrderResponseDTO;
import com.javiz.cars_ecommerce_backend.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/JCars/api/order")
public class OrderController {

    private final OrderService orderService;

    @GetMapping
    public ResponseEntity<List<OrderResponseDTO>> findAllOrders() {
        return ResponseEntity.status(HttpStatus.OK).body(orderService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<OrderResponseDTO> findOrderById(@PathVariable Long id) {
        return ResponseEntity.status(HttpStatus.OK).body(orderService.findById(id));
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<List<OrderResponseDTO>> findOrdersByUserId(@PathVariable Long userId) {
        return ResponseEntity.status(HttpStatus.OK).body(orderService.findByUserId(userId));
    }

    @GetMapping("/car/{carId}")
    public ResponseEntity<List<OrderResponseDTO>> findOrdersByCarId(@PathVariable Long carId) {
        return ResponseEntity.status(HttpStatus.OK).body(orderService.findByCarId(carId));
    }

    @GetMapping("/status/{status}")
    public ResponseEntity<List<OrderResponseDTO>> findOrdersByStatus(@PathVariable Integer status) {
        return ResponseEntity.status(HttpStatus.OK).body(orderService.findByStatus(status));
    }

    @PostMapping
    public ResponseEntity<OrderResponseDTO> createOrder(@RequestBody OrderRequestDTO order) {
        return ResponseEntity.status(HttpStatus.CREATED).body(orderService.save(order));
    }

    @PutMapping("/{id}")
    public ResponseEntity<OrderResponseDTO> updateOrder(
            @PathVariable Long id,
            @RequestBody OrderRequestDTO order) {
        return ResponseEntity.status(HttpStatus.OK).body(orderService.update(id, order));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteOrder(@PathVariable Long id) {
        orderService.deleteById(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body("Order deleted successfully");
    }
}
