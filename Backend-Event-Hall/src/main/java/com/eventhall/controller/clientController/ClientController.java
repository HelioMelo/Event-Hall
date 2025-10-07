package com.eventhall.controller.clientController;

import java.util.List;
import java.util.UUID;

import com.eventhall.dto.clientDTO.response.ClientResponseDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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


    @GetMapping("/{companyId}")
    public ResponseEntity<List<ClientResponseDTO>> listClients(
            @PathVariable UUID companyId) {
        List<ClientResponseDTO> clients = clientService.listClients(companyId);
        return ResponseEntity.ok(clients);
    }

    @GetMapping("/{companyId}/{clientId}")
    public ResponseEntity<ClientResponseDTO> getClientById(
            @PathVariable UUID companyId,
            @PathVariable UUID clientId) {
        ClientResponseDTO client = clientService.getClientById(companyId, clientId);
        return ResponseEntity.ok(client);
    }

    @PutMapping("/{companyId}/{clientId}")
    public ResponseEntity<ClientResponseDTO> updateClient(
            @PathVariable UUID companyId,
            @PathVariable UUID clientId,
            @RequestBody @Valid ClientRequestDTO clientRequestDTO) {

        ClientResponseDTO updatedClient = clientService.updateClient(companyId, clientId, clientRequestDTO);
        return ResponseEntity.ok(updatedClient);
    }

    @DeleteMapping("/{companyId}/{clientId}")
    public ResponseEntity<Void> deleteClient(
            @PathVariable UUID companyId,
            @PathVariable UUID clientId) {

        clientService.deleteClient(companyId, clientId);
        return ResponseEntity.noContent().build();
    }


}
