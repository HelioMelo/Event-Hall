package com.eventhall.controller.clientController;

import java.util.UUID;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.eventhall.dto.clientDTO.request.ClientRequestDTO;
import com.eventhall.service.clientService.ClientService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/clients")
@RequiredArgsConstructor
public class ClientController {

    private final ClientService clientService;


    @PostMapping("/{companyId}")
    public ResponseEntity<ClientRequestDTO> createClient(
            @PathVariable UUID companyId,
            @RequestBody @Valid ClientRequestDTO clientRequestDTO) {

        ClientRequestDTO createdClient = clientService.createClient(companyId, clientRequestDTO);
        return ResponseEntity.ok(createdClient);
    }

}
