package com.eventhall.dto.clientDTO.response;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

import com.eventhall.dto.addressDTO.response.AddressResponseDTO;
import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ClientResponseDTO {
    private String name;
    private String email;
    private String contact;
    private List<AddressResponseDTO> addressList;
    private LocalDate birthDate;
    private LocalDate clientSince;
    private Integer totalEvents;
    private BigDecimal totalSpent;
    private String status;
    private String notes;
}

