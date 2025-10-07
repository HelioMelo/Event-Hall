package com.eventhall.service.clientService;

import com.eventhall.dto.clientDTO.request.ClientRequestDTO;
import com.eventhall.entities.Client;
import com.eventhall.entities.Companies;
import com.eventhall.mappers.clientMapper.ClientMapper;
import lombok.experimental.UtilityClass;

@UtilityClass
public class ClientFactory {

    public Client createOrReactivateClient(Client existingClient, ClientRequestDTO dto, Companies company) {
        if (existingClient != null) {
            existingClient.setIsActive(true);
            ClientMapper.updateEntityFromDTO(dto, existingClient);
            return existingClient;
        }

        Client client = new Client();
        ClientMapper.updateEntityFromDTO(dto, client);
        client.setCompany(company);
        client.setIsActive(true);
        return client;
    }
}
