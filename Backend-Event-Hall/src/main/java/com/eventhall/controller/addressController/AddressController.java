package com.eventhall.controller.addressController;

import com.eventhall.dto.addressDTO.request.AddressRequestDTO;
import com.eventhall.dto.addressDTO.response.AddressResponseDTO;
import com.eventhall.service.addressService.AddressService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/address")
@RequiredArgsConstructor
public class AddressController {

    private final AddressService addressService;


    @PostMapping("/client/{clientId}/company/{companyId}")
    public ResponseEntity<AddressResponseDTO> createOrUpdateAddress(
            @PathVariable UUID companyId,
            @PathVariable UUID clientId,
            @RequestBody AddressRequestDTO dto
    ) {
        AddressResponseDTO responseDTO = addressService.createOrUpdateAddress(companyId, clientId, dto);
        return ResponseEntity.ok(responseDTO);
    }

    @PutMapping("/client/{clientId}/company/{companyId}/address/{addressId}")
    public ResponseEntity<AddressResponseDTO> updateAddress(
            @PathVariable UUID companyId,
            @PathVariable UUID clientId,
            @PathVariable UUID addressId,
            @RequestBody AddressRequestDTO dto
    ) {
        AddressResponseDTO responseDTO = addressService.updateAddress(companyId, clientId, addressId, dto);
        return ResponseEntity.ok(responseDTO);
    }

}