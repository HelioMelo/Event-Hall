package com.eventhall.dto.request;

import jakarta.validation.constraints.NotBlank;

public record RegisterDTO(
        @NotBlank(message = "O campo 'emailUser' é obrigatório")
        String emailUser,

        @NotBlank(message = "O campo 'passwordUser' é obrigatório")
        String passwordUser,

        @NotBlank(message = "O campo 'nameUser' é obrigatório")
        String nameUser,

        @NotBlank(message = "O campo 'nameCompany' é obrigatório")
        String nameCompany,

        @NotBlank(message = "O campo 'documentCompany' é obrigatório")
        String documentCompany
) {}