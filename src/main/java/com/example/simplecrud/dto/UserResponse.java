package com.example.simplecrud.dto;

public record UserResponse(
        Long id,
        String username,
        String email
) {}