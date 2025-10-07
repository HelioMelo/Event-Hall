package com.eventhall.service.addressService.factory;


import com.eventhall.dto.addressDTO.request.AddressRequestDTO;
import com.eventhall.entities.Address;
import com.eventhall.entities.Client;
import com.eventhall.entities.Companies;
import com.eventhall.mappers.addressMapper.AddressMapper;

public class AddressFactory {

    public static Address createAddress(Client client, Companies company, AddressRequestDTO dto) {
        Address address = AddressMapper.toEntity(dto);
        address.setClient(client);
        address.setCompany(company);
        address.setIsActive(true);
        return address;
    }
}

