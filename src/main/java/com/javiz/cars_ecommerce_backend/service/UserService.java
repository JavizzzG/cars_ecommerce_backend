package com.javiz.cars_ecommerce_backend.service;

import com.javiz.cars_ecommerce_backend.dto.UserDTO.UserRequestDTO;
import com.javiz.cars_ecommerce_backend.dto.UserDTO.UserResponseDTO;
import com.javiz.cars_ecommerce_backend.entity.User;
import com.javiz.cars_ecommerce_backend.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    public List<UserResponseDTO> findAll(){
        return userRepository.findAll().stream()
                .map(this::convertToDTO)
                .toList();
    }

    public UserResponseDTO findById(Long id){
        return userRepository.findById(id)
                .map(this::convertToDTO)
                .orElseThrow(() -> new RuntimeException("User not found"));
    }

    public UserResponseDTO save(UserRequestDTO userRequest){

        User user = convertToEntity(userRequest);

        return convertToDTO(userRepository.save(user));

    }

    public UserResponseDTO update(Long id, UserRequestDTO userRequestDTO){

        User user = userRepository.findById(id).orElseThrow(() -> new RuntimeException("User was not found"));

        user.setName(userRequestDTO.getName());
        user.setEmail(userRequestDTO.getEmail());
        user.setPassword(userRequestDTO.getPassword());
        user.setBirthdate(userRequestDTO.getBirthdate());
        user.setDocument(userRequestDTO.getDocument());
        user.setPhone(userRequestDTO.getPhone());

        return convertToDTO(userRepository.save(user));

    }

    public void deleteById(Long id){

        userRepository.findById(id).orElseThrow(() -> new RuntimeException("user was not found"));
        userRepository.deleteById(id);

    }






    private User convertToEntity(UserRequestDTO user){
        User userEntity = new User();
        userEntity.setName(user.getName());
        userEntity.setEmail(user.getEmail());
        userEntity.setPassword(user.getPassword());
        userEntity.setBirthdate(user.getBirthdate());
        userEntity.setDocument(user.getDocument());
        userEntity.setPhone(user.getPhone());
        return userEntity;
    }

    private UserResponseDTO convertToDTO(User user){
        UserResponseDTO userDTO = new UserResponseDTO();
        userDTO.setId(user.getId());
        userDTO.setName(user.getName());
        userDTO.setEmail(user.getEmail());
        userDTO.setPassword(user.getPassword());
        userDTO.setBirthdate(user.getBirthdate());
        userDTO.setDocument(user.getDocument());
        userDTO.setPhone(user.getPhone());
        return userDTO;
    }

}
