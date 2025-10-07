package com.eventhall.mappers.addressMapper;

import com.eventhall.dto.addressDTO.request.AddressRequestDTO;
import com.eventhall.dto.addressDTO.response.AddressResponseDTO;
import com.eventhall.entities.Address;
import org.springframework.stereotype.Component;

@Component
public class AddressMapper {

    private AddressMapper() {}

    public static Address toEntity(AddressRequestDTO dto) {
        if (dto == null) return null;
        Address address = new Address();
        updateBasicFields(address, dto);
        address.setIsActive(true);
        return address;
    }

    public static AddressRequestDTO toRequestDTO(Address address) {
        if (address == null || !Boolean.TRUE.equals(address.getIsActive())) return null;
        AddressRequestDTO dto = new AddressRequestDTO();
        updateDTOFields(dto, address);
        return dto;
    }

    public static AddressResponseDTO toResponseDTO(Address address) {
        if (address == null) return null;
        return AddressResponseDTO.builder()
                .neighborhood(address.getNeighborhood())
                .zipCode(address.getZipCode())
                .street(address.getStreet())
                .city(address.getCity())
                .state(address.getState())
                .complement(address.getComplement())
                .number(address.getNumber())
                .build();
    }

    public static void updateEntityFromDTO(AddressRequestDTO dto, Address address) {
        if (dto == null || address == null) return;
        updateBasicFields(address, dto);
    }

    private static void updateBasicFields(Address address, AddressRequestDTO dto) {
        address.setNeighborhood(dto.getNeighborhood());
        address.setStreet(dto.getStreet());
        address.setCity(dto.getCity());
        address.setState(dto.getState());
        address.setZipCode(dto.getZipCode());
        address.setNumber(dto.getNumber());
        address.setComplement(dto.getComplement());
    }

    private static void updateDTOFields(AddressRequestDTO dto, Address address) {
        dto.setNeighborhood(address.getNeighborhood());
        dto.setStreet(address.getStreet());
        dto.setCity(address.getCity());
        dto.setState(address.getState());
        dto.setZipCode(address.getZipCode());
        dto.setNumber(address.getNumber());
        dto.setComplement(address.getComplement());
    }
}
