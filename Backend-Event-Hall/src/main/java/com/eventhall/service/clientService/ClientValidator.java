package com.eventhall.service.clientService;

import com.eventhall.entities.Client;
import com.eventhall.exception.ClientNotFoundException;
import com.eventhall.repository.ClientRepository;
import com.eventhall.exception.ClientAlreadyExistsException;
import com.eventhall.exception.CompanyNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Component;

import java.util.Locale;

@Component
@RequiredArgsConstructor
public class ClientValidator {

    private final ClientRepository clientRepository;
    private final MessageSource messageSource;

    // Valida se o cliente existe e está ativo
    public void validateClientExists(Client client) {
        if (client == null || !Boolean.TRUE.equals(client.getIsActive())) {
            throw new ClientNotFoundException(
                    messageSource.getMessage(
                            "error.client.not.found",
                            null,
                            "Cliente não encontrado",
                            Locale.getDefault()
                    )
            );
        }
    }

    // Valida se já existe um cliente ativo com os mesmos dados
    public void validateClientDuplicate(Client existingClient) {
        if (existingClient != null && Boolean.TRUE.equals(existingClient.getIsActive())) {
            throw new ClientAlreadyExistsException(
                    messageSource.getMessage(
                            "error.client.already.exists",
                            null,
                            "Cliente já existe",
                            Locale.getDefault()
                    )
            );
        }
    }

    // Valida se a empresa existe
    public void validateCompanyExists(Object company, String defaultMsg) {
        if (company == null) {
            throw new CompanyNotFoundException(
                    messageSource.getMessage(
                            "error.company.not.found",
                            null,
                            defaultMsg,
                            Locale.getDefault()
                    )
            );
        }
    }
}
