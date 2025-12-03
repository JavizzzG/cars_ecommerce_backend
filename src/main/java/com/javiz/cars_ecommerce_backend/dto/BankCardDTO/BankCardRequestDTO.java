package com.javiz.cars_ecommerce_backend.dto.BankCardDTO;

import lombok.Data;
import java.math.BigDecimal;

@Data
public class BankCardRequestDTO {
    private String fkcodeBank;
    private String number;
    private String cvc;
    private String name;
    private String expire_date;
    private BigDecimal balance;
    private Integer status;
}
