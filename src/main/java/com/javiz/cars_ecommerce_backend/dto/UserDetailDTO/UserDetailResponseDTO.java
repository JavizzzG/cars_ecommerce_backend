package com.javiz.cars_ecommerce_backend.dto.UserDetailDTO;

import lombok.Data;

@Data
public class UserDetailResponseDTO {

    private Long id;
    private Long fkidUser;
    private String description;
    private String image;

}
