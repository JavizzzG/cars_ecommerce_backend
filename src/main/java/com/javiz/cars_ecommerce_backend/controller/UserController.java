package com.javiz.cars_ecommerce_backend.controller;

import com.javiz.cars_ecommerce_backend.dto.UserDTO.UserRequestDTO;
import com.javiz.cars_ecommerce_backend.dto.UserDTO.UserResponseDTO;
import com.javiz.cars_ecommerce_backend.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/cars/")
public class UserController {

    private final UserService userService;

    @GetMapping
    public ResponseEntity<List<UserResponseDTO>> findAllUsers(){

        List<UserResponseDTO> users = userService.findAll();

        return ResponseEntity.status(HttpStatus.OK).body(users);

    }

    @GetMapping("{id}")
    public ResponseEntity<UserResponseDTO> findUserById(@PathVariable Long id) {
        return ResponseEntity.status(HttpStatus.OK).body(userService.findById(id));
    }

    @PostMapping
    public ResponseEntity<UserResponseDTO> saveUser(@RequestBody UserRequestDTO user){
        return ResponseEntity.status(HttpStatus.CREATED).body(userService.save(user));
    }

    @PutMapping ResponseEntity<UserResponseDTO> updateUser(@PathVariable Long id, @RequestBody UserRequestDTO user){
        return ResponseEntity.status(HttpStatus.OK).body(userService.update(id, user));
    }

    @DeleteMapping ResponseEntity<String> deleteUserById(@PathVariable Long id){
        userService.deleteById(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body("User was deleted successfully");
    }


}
