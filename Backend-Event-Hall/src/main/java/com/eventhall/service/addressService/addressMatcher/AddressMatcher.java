package com.eventhall.service.addressService.addressMatcher;

import com.eventhall.dto.addressDTO.request.AddressRequestDTO;
import com.eventhall.entities.Address;

public interface AddressMatcher {
    boolean matches(AddressRequestDTO dto, Address address);
}
