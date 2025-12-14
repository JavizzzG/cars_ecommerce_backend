package com.javiz.cars_ecommerce_backend.service;

import com.javiz.cars_ecommerce_backend.dto.UserDTO.LoginDTO;
import com.javiz.cars_ecommerce_backend.dto.UserDTO.UserRequestDTO;
import com.javiz.cars_ecommerce_backend.dto.UserDTO.UserResponseDTO;
import com.javiz.cars_ecommerce_backend.entity.User;
import com.javiz.cars_ecommerce_backend.repository.UserRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Transactional
    public List<UserResponseDTO> findAll(){
        return userRepository.findAll().stream()
                .map(this::convertToDTO)
                .toList();
    }

    @Transactional
    public UserResponseDTO findById(Long id){
        return userRepository.findById(id)
                .map(this::convertToDTO)
                .orElseThrow(() -> new RuntimeException("User not found"));
    }

    @Transactional
    public UserResponseDTO findByEmail(String email){
        return userRepository.findByEmail(email)
                .map(this::convertToDTO)
                .orElseThrow(() -> new RuntimeException("User was not found"));
    }

    @Transactional
    public UserResponseDTO login(LoginDTO loginDTO){

        UserResponseDTO user = findByEmail(loginDTO.getEmail());

        if(!passwordEncoder.matches(loginDTO.getPassword(), user.getPassword())){
            throw new RuntimeException("Invalid password");
        }else{
            return user;
        }

    }

    @Transactional
    public UserResponseDTO save(UserRequestDTO userRequest){

        if(userRepository.findByEmail(userRequest.getEmail()).isPresent()){
            throw new RuntimeException("User already exists");
        }

        userRequest.setPassword(passwordEncoder.encode(userRequest.getPassword()));

        User user = convertToEntity(userRequest);

        return convertToDTO(userRepository.save(user));

    }

    @Transactional
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

    @Transactional
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
