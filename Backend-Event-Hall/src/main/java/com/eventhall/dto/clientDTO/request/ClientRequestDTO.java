package com.eventhall.dto.clientDTO.request;



import java.time.LocalDate;
import java.util.Set;

import com.eventhall.dto.addressDTO.request.AddressRequestDTO;
import com.eventhall.entities.enums.ClientStatusEnum;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ClientRequestDTO {

    @NotEmpty(message = "O nome do cliente é obrigatório")
    @Size(max = 50, message = "O nome do cliente deve ter no máximo 50 caracteres")
    private String name;

    private String notes;

    @NotEmpty(message = "Documento é obrigatório")
    @Size(min = 11, max = 14, message = "Documento deve ter entre 11 e 14 caracteres")
    private String document;

    @NotEmpty(message = "Contato é obrigatório")
    @Size(min = 8, max = 11, message = "Contato deve ter entre 8 e 11 caracteres")
    private String contact;

    @Past(message = "Data de nascimento deve estar no passado")
    private LocalDate birthDate;

    private LocalDate registrationDate;

    private ClientStatusEnum status;

    @Size(max = 255, message = "URL do logo deve ter no máximo 255 caracteres")
    private String logoUrl;

    @NotEmpty(message = "Email é obrigatório")
    @Email(message = "Email deve ser válido")
    @Size(max = 100, message = "Email deve ter no máximo 100 caracteres")
    private String email;

    @Valid
    private Set<AddressRequestDTO> addressList;

}
