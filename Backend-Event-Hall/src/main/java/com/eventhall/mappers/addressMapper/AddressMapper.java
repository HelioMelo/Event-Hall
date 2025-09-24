package com.eventhall.mappers.addressMapper;


import com.eventhall.dto.addressDTO.request.AddressRequestDTO;
import com.eventhall.entities.Address;

public class AddressMapper {

    // DTO -> Entidade
    public static Address toEntity(AddressRequestDTO dto) {
        if (dto == null) return null;

        Address address = new Address();
        address.setNeighborhood(dto.getNeighborhood());
        address.setStreet(dto.getStreet());
        address.setCity(dto.getCity());
        address.setState(dto.getState());
        address.setZipCode(dto.getZipCode());
        address.setNumber(dto.getNumber());
        address.setComplement(dto.getComplement());
        return address;
    }

    // Entidade -> DTO
    public static AddressRequestDTO toDTO(Address entity) {
        if (entity == null) return null;

        AddressRequestDTO dto = new AddressRequestDTO();
        dto.setNeighborhood(entity.getNeighborhood());
        dto.setStreet(entity.getStreet());
        dto.setCity(entity.getCity());
        dto.setState(entity.getState());
        dto.setZipCode(entity.getZipCode());
        dto.setNumber(entity.getNumber());
        dto.setComplement(entity.getComplement());
        return dto;
    }
}
