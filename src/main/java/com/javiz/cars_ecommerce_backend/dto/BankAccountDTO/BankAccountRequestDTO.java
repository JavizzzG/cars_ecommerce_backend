package com.javiz.cars_ecommerce_backend.dto.BankAccountDTO;

import lombok.Data;
import java.math.BigDecimal;

@Data
public class BankAccountRequestDTO {
    private String fkcodeBank;
    private String document;
    private String name;
    private String password;
    private BigDecimal balance;
    private Integer status;
}
