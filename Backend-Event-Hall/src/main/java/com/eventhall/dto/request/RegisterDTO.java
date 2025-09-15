package com.eventhall.dto.request;

import jakarta.validation.constraints.NotNull;

public record RegisterDTO(
    @NotNull(message = "O campo 'emailUser' é obrigatório") String emailUser,
    @NotNull(message = "O campo 'passwordUser' é obrigatório") String passwordUser,
    @NotNull(message = "O campo 'nameUser' é obrigatório") String nameUser,
    @NotNull(message = "O campo 'nameCompany' é obrigatório") String nameCompany,
    @NotNull(message = "O campo 'documentCompany' é obrigatório") String documentCompany
) {}