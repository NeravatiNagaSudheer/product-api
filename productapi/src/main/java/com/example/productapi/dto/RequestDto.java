package com.example.productapi.dto;

import com.example.productapi.entity.Product;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import java.math.BigDecimal;

@Data
public class RequestDto extends Product {
    @NotBlank
    private String productName;
    @NotNull
    private BigDecimal price;
    @Min(1)
    private int quantity;

//    Controller → RequestDto
//    Service → Entity (internal)
//    Service → ResponseDto
//    Controller → ResponseDto
}
