package com.eventhall.dto.request;



import jakarta.validation.constraints.NotBlank;

public record LoginDTO(
    @NotBlank(message = "Email é obrigatório") String email, 
    @NotBlank(message = "Password é obrigatório") String password
) {
}