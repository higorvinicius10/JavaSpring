package com.example.demo.User.DTO;

import java.util.UUID;

public record UserResponseDTO(
        UUID id,
        String name,
        String userName
) {
}