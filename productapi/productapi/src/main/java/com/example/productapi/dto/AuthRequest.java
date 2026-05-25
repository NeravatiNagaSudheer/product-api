package com.example.productapi.dto;

import lombok.Data;

@Data
public class AuthRequest {
    private String username;
    private String password;
}
