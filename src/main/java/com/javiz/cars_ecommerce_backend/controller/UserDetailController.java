package com.javiz.cars_ecommerce_backend.controller;

import com.javiz.cars_ecommerce_backend.dto.UserDetailDTO.UserDetailRequestDTO;
import com.javiz.cars_ecommerce_backend.dto.UserDetailDTO.UserDetailResponseDTO;
import com.javiz.cars_ecommerce_backend.service.UserDetailService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/JCars/api/user-detail")
public class UserDetailController {

    private final UserDetailService userDetailService;

    @GetMapping
    public List<UserDetailResponseDTO> findAllUserDetail(){
        return userDetailService.findAll();
    }

    @GetMapping("/{id}")
    public UserDetailResponseDTO findUserdetailById(@PathVariable Long id){
        return userDetailService.findById(id);
    }

    @GetMapping("/user/{id}")
    public UserDetailResponseDTO findUserDetailByUserId(@PathVariable Long id){
        return userDetailService.findByUserId(id);
    }

    @PostMapping
    public UserDetailResponseDTO saveUserDetail(@RequestBody UserDetailRequestDTO user){
        return userDetailService.save(user);
    }

    @PutMapping("/{id}")
    public UserDetailResponseDTO updateUserDetail(@PathVariable Long id, @RequestBody UserDetailRequestDTO user){
        return userDetailService.update(id, user);
    }

    @DeleteMapping("/{id}")
    public void deleteUserDetailById(@PathVariable Long id){
        userDetailService.deleteById(id);
    }

}
