package com.eventhall.service.addressService.addressMatcher;

import com.eventhall.dto.addressDTO.request.AddressRequestDTO;
import com.eventhall.entities.Address;
import org.springframework.stereotype.Component;

import java.util.Objects;

@Component
public class DefaultAddressMatcher implements AddressMatcher {

    @Override
    public boolean matches(AddressRequestDTO dto, Address address) {
        return Objects.equals(dto.getZipCode(), address.getZipCode()) &&
                Objects.equals(dto.getNumber(), address.getNumber());
    }
}
