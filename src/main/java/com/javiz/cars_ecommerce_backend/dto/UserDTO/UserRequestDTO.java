package com.javiz.cars_ecommerce_backend.dto.UserDTO;

import lombok.Data;

import java.util.Date;

@Data
public class UserRequestDTO {

    private String name;
    private String email;
    private String password;
    private Date birthdate;
    private String document;
    private String phone;

}
