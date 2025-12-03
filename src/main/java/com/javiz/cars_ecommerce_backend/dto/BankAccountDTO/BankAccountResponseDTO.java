package com.javiz.cars_ecommerce_backend.dto.BankAccountDTO;

import lombok.Data;
import java.math.BigDecimal;

@Data
public class BankAccountResponseDTO {
    private Long id;
    private String fkcodeBank;
    private String document;
    private String name;
    private BigDecimal balance;
    private Integer status;
    // Note: Password is intentionally excluded from the response DTO for security
}
