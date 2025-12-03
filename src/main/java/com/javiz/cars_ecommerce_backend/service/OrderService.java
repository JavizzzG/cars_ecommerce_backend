package com.javiz.cars_ecommerce_backend.service;

import com.javiz.cars_ecommerce_backend.dto.OrderDTO.OrderRequestDTO;
import com.javiz.cars_ecommerce_backend.dto.OrderDTO.OrderResponseDTO;
import com.javiz.cars_ecommerce_backend.entity.Order;
import com.javiz.cars_ecommerce_backend.repository.OrderRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderService {

    private final OrderRepository orderRepository;

    @Transactional
    public List<OrderResponseDTO> findAll() {
        return orderRepository.findAll().stream()
                .map(this::convertToDTO)
                .toList();
    }

    @Transactional
    public OrderResponseDTO findById(Long id) {
        return orderRepository.findById(id)
                .map(this::convertToDTO)
                .orElseThrow(() -> new RuntimeException("Order not found"));
    }

    @Transactional
    public List<OrderResponseDTO> findByUserId(Long id_user) {
        return orderRepository.findByFkidUser(id_user).stream()
                .map(this::convertToDTO)
                .toList();
    }

    @Transactional
    public List<OrderResponseDTO> findByCarId(Long id_car) {
        return orderRepository.findByFkidCar(id_car).stream()
                .map(this::convertToDTO)
                .toList();
    }

    @Transactional
    public List<OrderResponseDTO> findByStatus(Integer status) {
        return orderRepository.findByStatus(status).stream()
                .map(this::convertToDTO)
                .toList();
    }

    @Transactional
    public OrderResponseDTO save(OrderRequestDTO orderDTO) {
        Order order = convertToEntity(orderDTO);
        return convertToDTO(orderRepository.save(order));
    }

    @Transactional
    public OrderResponseDTO update(Long id, OrderRequestDTO orderDTO) {
        Order order = orderRepository.findById(id).orElseThrow(() -> new RuntimeException("Order not found"));
        
        order.setFkidUser(orderDTO.getFkidUser());
        order.setFkidCar(orderDTO.getFkidCar());
        order.setAmount(orderDTO.getAmount());
        order.setDate(orderDTO.getDate());
        order.setAddress(orderDTO.getAddress());
        order.setPhone(orderDTO.getPhone());
        order.setStatus(orderDTO.getStatus());
        
        return convertToDTO(orderRepository.save(order));
    }

    @Transactional
    public void deleteById(Long id) {
        Order order = orderRepository.findById(id).orElseThrow(() -> new RuntimeException("Order not found"));
        orderRepository.delete(order);
    }








    private Order convertToEntity(OrderRequestDTO dto) {
        Order order = new Order();
        order.setFkidUser(dto.getFkidUser());
        order.setFkidCar(dto.getFkidCar());
        order.setAmount(dto.getAmount());
        order.setDate(dto.getDate() != null ? dto.getDate() : LocalDateTime.now());
        order.setAddress(dto.getAddress());
        order.setPhone(dto.getPhone());
        order.setStatus(dto.getStatus() != null ? dto.getStatus() : 0); // Default status
        return order;
    }

    private OrderResponseDTO convertToDTO(Order order) {
        OrderResponseDTO dto = new OrderResponseDTO();
        dto.setId(order.getId());
        dto.setFkidUser(order.getFkidUser());
        dto.setFkidCar(order.getFkidCar());
        dto.setAmount(order.getAmount());
        dto.setDate(order.getDate());
        dto.setAddress(order.getAddress());
        dto.setPhone(order.getPhone());
        dto.setStatus(order.getStatus());
        return dto;
    }
}
