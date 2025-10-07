package com.eventhall.service.addressService;

import com.eventhall.dto.addressDTO.request.AddressRequestDTO;
import com.eventhall.entities.Client;
import com.eventhall.entities.Companies;

import java.util.Set;

public interface IAddressService {
    void updateClientAddresses(Client client, Set<AddressRequestDTO> addressDTOs, Companies company);


}
