package com.eventhall.mappers.clientMapper;

import com.eventhall.dto.clientDTO.request.ClientRequestDTO;
import com.eventhall.dto.clientDTO.response.ClientResponseDTO;
import com.eventhall.entities.Address;
import com.eventhall.entities.Client;
import com.eventhall.mappers.addressMapper.AddressMapper;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.Objects;
import java.util.stream.Collectors;

@Component
public class ClientMapper {

    private ClientMapper() {}

    public static ClientRequestDTO toClientRequestDTO(Client client) {
        if (client == null) return null;

        ClientRequestDTO dto = new ClientRequestDTO();
        dto.setName(client.getName());
        dto.setEmail(client.getEmail());
        dto.setContact(client.getContact());
        dto.setDocument(client.getDocument());
        dto.setBirthDate(client.getBirthDate());
        dto.setLogoUrl(client.getLogoUrl());
        dto.setNotes(client.getNotes());
        dto.setStatus(client.getStatus());
        dto.setRegistrationDate(client.getRegistrationDate());

        if (client.getAddressList() != null) {
            dto.setAddressList(
                    client.getAddressList().stream()
                            .filter(Objects::nonNull)
                            .filter(Address::getIsActive)
                            .map(AddressMapper::toRequestDTO)
                    .collect(Collectors.toSet()));
        }

        return dto;
    }

    public static ClientResponseDTO toClientListResponseDTO(Client client) {
        if (client == null) return null;

        return ClientResponseDTO.builder()
                .name(client.getName())
                .email(client.getEmail())
                .contact(client.getContact())
                .addressList(client.getAddressList() != null ?
                        client.getAddressList().stream()
                                .filter(a -> Boolean.TRUE.equals(a.getIsActive()))
                                .map(AddressMapper::toResponseDTO)
                                .collect(Collectors.toList()) : null)
                .birthDate(client.getBirthDate())
                .clientSince(client.getRegistrationDate())
                .totalEvents(client.getEvents() != null ? client.getEvents().size() : 0)
                .totalSpent(BigDecimal.ZERO)
                .status(client.getStatus() != null ? client.getStatus().name() : null)
                .notes(client.getNotes())
                .build();
    }

    public static void updateEntityFromDTO(ClientRequestDTO dto, Client client) {
        if (dto == null || client == null) return;

        client.setName(dto.getName());
        client.setEmail(dto.getEmail());
        client.setContact(dto.getContact());
        client.setDocument(dto.getDocument());
        client.setBirthDate(dto.getBirthDate());
        client.setLogoUrl(dto.getLogoUrl());
        client.setNotes(dto.getNotes());
        client.setStatus(dto.getStatus());
        client.setRegistrationDate(dto.getRegistrationDate());
    }
}
