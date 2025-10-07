package com.eventhall.dto.addressDTO.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AddressResponseDTO {
    private String neighborhood;
    private String zipCode;
    private String street;
    private String city;
    private String state;
    private String complement;
    private String number;
}
