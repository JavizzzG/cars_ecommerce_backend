package com.javiz.cars_ecommerce_backend.service;

import com.javiz.cars_ecommerce_backend.dto.UserDetailDTO.UserDetailRequestDTO;
import com.javiz.cars_ecommerce_backend.dto.UserDetailDTO.UserDetailResponseDTO;
import com.javiz.cars_ecommerce_backend.entity.UserDetail;
import com.javiz.cars_ecommerce_backend.repository.UserDetailRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserDetailService {

    private final UserDetailRepository userDetailRepository;

    public List<UserDetailResponseDTO> findAll(){
        return userDetailRepository.findAll().stream()
                .map(this::convertToDTO)
                .toList();
    }

    public UserDetailResponseDTO findById(Long id){
        return userDetailRepository.findById(id)
                .map(this::convertToDTO)
                .orElseThrow(() -> new RuntimeException("UserDetail was not found"));
    }

    public UserDetailResponseDTO findByUserId(Long id){
        return userDetailRepository.findByFkidUser(id)
                .map(this::convertToDTO)
                .orElseThrow(() -> new RuntimeException("UserDetail was not found"));
    }

    public UserDetailResponseDTO save(UserDetailRequestDTO user){
        return convertToDTO(userDetailRepository.save(convertToEntity(user)));
    }

    public UserDetailResponseDTO update(Long id, UserDetailRequestDTO user){
        UserDetail userDetail = userDetailRepository.findById(id).orElseThrow(() -> new RuntimeException("UserDetail was not found"));
        userDetail.setFkidUser(user.getFkidUser());
        userDetail.setDescription(user.getDescription());
        userDetail.setImage(user.getImage());
        return convertToDTO(userDetailRepository.save(userDetail));
    }

    public void deleteById(Long id){
        userDetailRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("UserDetail was not found"));
        userDetailRepository.deleteById(id);
    }








    private UserDetail convertToEntity(UserDetailRequestDTO user){
        UserDetail userDetail = new UserDetail();
        userDetail.setFkidUser(user.getFkidUser());
        userDetail.setDescription(user.getDescription());
        userDetail.setImage(user.getImage());
        return userDetail;
    }

    private UserDetailResponseDTO convertToDTO(UserDetail user){
        UserDetailResponseDTO userDetailResponseDTO = new UserDetailResponseDTO();
        userDetailResponseDTO.setId(user.getId());
        userDetailResponseDTO.setFkidUser(user.getFkidUser());
        userDetailResponseDTO.setDescription(user.getDescription());
        userDetailResponseDTO.setImage(user.getImage());
        return userDetailResponseDTO;
    }

}
