package com.javiz.cars_ecommerce_backend.dto.PaymentDTO;

import lombok.Data;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
public class PaymentResponseDTO {
    private Long id;
    private String fkidOrder;
    private String amount;
    private String fkcodeBank;
    private Integer status;
    private LocalDateTime created_at;
    private LocalDateTime updated_at;
}
