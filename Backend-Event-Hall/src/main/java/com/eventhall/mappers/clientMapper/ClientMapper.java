package com.eventhall.mappers.clientMapper;

import java.util.stream.Collectors;

import com.eventhall.dto.clientDTO.request.ClientRequestDTO;
import com.eventhall.entities.Client;
import com.eventhall.mappers.addressMapper.AddressMapper;

public class ClientMapper {

    // DTO -> Entidade
    public static Client toEntity(ClientRequestDTO dto) {
        if (dto == null) return null;

        Client client = new Client();
        client.setName(dto.getName());
        client.setDocument(dto.getDocument());
        client.setEmail(dto.getEmail());
        client.setContact(dto.getContact());
        client.setBirthDate(dto.getBirthDate());
        client.setRegistrationDate(dto.getRegistrationDate());
        client.setStatus(dto.getStatus());
        client.setNotes(dto.getNotes());
        client.setLogoUrl(dto.getLogoUrl());

        // Converte endereços - verifique se AddressMapper::toEntity existe
        if (dto.getAddressList() != null) {
            client.setAddressList(
                    dto.getAddressList().stream()
                            .map(AddressMapper::toEntity)
                            .peek(address -> address.setClient(client))
                            .collect(Collectors.toSet())
            );
        }

        return client;
    }

    // Entidade -> DTO
    public static ClientRequestDTO toDTO(Client client) {
        if (client == null) return null;

        ClientRequestDTO dto = new ClientRequestDTO();
        dto.setName(client.getName());
        dto.setDocument(client.getDocument());
        dto.setEmail(client.getEmail());
        dto.setContact(client.getContact());
        dto.setBirthDate(client.getBirthDate());
        dto.setRegistrationDate(client.getRegistrationDate());
        dto.setStatus(client.getStatus());
        dto.setNotes(client.getNotes());
        dto.setLogoUrl(client.getLogoUrl());

        // Converte endereços - verifique o tipo de retorno do AddressMapper::toDTO
        if (client.getAddressList() != null) {
            dto.setAddressList(
                    client.getAddressList().stream()
                            .map(AddressMapper::toDTO)
                            .collect(Collectors.toSet())
            );
        }

        return dto;
    }
}