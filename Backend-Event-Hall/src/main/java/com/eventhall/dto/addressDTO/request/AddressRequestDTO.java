package com.eventhall.dto.addressDTO.request;


import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AddressRequestDTO {

    @NotEmpty(message = "Bairro é obrigatório")
    @Size(max = 150, message = "Bairro deve ter no máximo 150 caracteres")
    private String neighborhood;

    @NotEmpty(message = "CEP é obrigatório")
    @Size(min = 8, max = 8, message = "CEP deve ter exatamente 8 caracteres")
    private String zipCode;

    @NotEmpty(message = "Rua é obrigatório")
    @Size(max = 150, message = "Rua deve ter no máximo 150 caracteres")
    private String street;

    @NotEmpty(message = "Cidade é obrigatório")
    @Size(max = 40, message = "Cidade deve ter no máximo 40 caracteres")
    private String city;

    @NotEmpty(message = "Estado é obrigatório")
    @Size(max = 150, message = "Estado deve ter no máximo 150 caracteres")
    private String state;

    @Size(max = 100, message = "Complemento deve ter no máximo 100 caracteres")
    private String complement;

    private String number;
}
