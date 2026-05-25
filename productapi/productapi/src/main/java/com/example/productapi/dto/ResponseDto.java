package com.example.productapi.dto;

import com.example.productapi.entity.Product;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class ResponseDto extends Product {
    private Long id;
    private String productName;
    private BigDecimal price;
    private int quantity;

}
