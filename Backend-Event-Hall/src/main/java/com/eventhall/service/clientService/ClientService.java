package com.eventhall.service.clientService;

import com.eventhall.dto.clientDTO.request.ClientRequestDTO;
import com.eventhall.dto.clientDTO.response.ClientResponseDTO;
import com.eventhall.entities.Client;
import com.eventhall.entities.Companies;
import com.eventhall.mappers.clientMapper.ClientMapper;
import com.eventhall.repository.ClientRepository;
import com.eventhall.repository.CompaniesRepository;
import com.eventhall.service.addressService.IAddressService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;


@Service
@RequiredArgsConstructor
public class ClientService {

    private final CompaniesRepository companiesRepository;
    private final ClientRepository clientRepository;
    private final IAddressService addressService;

    private final ClientValidator clientValidator;

    private Companies getCompany(UUID companyId) {
        Companies company = companiesRepository.findById(companyId).orElse(null);
        clientValidator.validateCompanyExists(company, "Empresa não encontrada");
        return company;
    }

    private Client findClient(UUID companyId, UUID clientId) {
        Companies company = getCompany(companyId);
        Client client = clientRepository.findByIdAndCompany_IdAndIsActiveTrue(clientId, company.getId()).orElse(null);
        clientValidator.validateClientExists(client);
        return client;
    }

    private Client findOrCreateClient(Companies company, ClientRequestDTO dto) {
        Client existingClient = clientRepository.findByDocumentAndCompany_Id(dto.getDocument(), company.getId())
                .orElse(null);

        clientValidator.validateClientDuplicate(existingClient);

        return ClientFactory.createOrReactivateClient(existingClient, dto, company);
    }

    private void deactivateClient(Client client) {
        client.setIsActive(false);
        if (client.getAddressList() != null) {
            client.getAddressList().forEach(a -> a.setIsActive(false));
        }
    }

    // -------------------------
    // Métodos públicos
    // -------------------------
    @Transactional
    public ClientRequestDTO createClient(UUID companyId, ClientRequestDTO dto) {
        Companies company = getCompany(companyId);
        Client client = findOrCreateClient(company, dto);
        addressService.updateClientAddresses(client, dto.getAddressList(), company);
        return ClientMapper.toClientRequestDTO(clientRepository.save(client));
    }

    @Transactional(readOnly = true)
    public List<ClientResponseDTO> listClients(UUID companyId) {
        Companies company = getCompany(companyId);
        return clientRepository.findByCompany_IdAndIsActiveTrue(company.getId())
                .stream()
                .map(ClientMapper::toClientListResponseDTO)
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public ClientResponseDTO getClientById(UUID companyId, UUID clientId) {
        Client client = findClient(companyId, clientId);
        return ClientMapper.toClientListResponseDTO(client);
    }

    @Transactional
    public ClientResponseDTO updateClient(UUID companyId, UUID clientId, ClientRequestDTO dto) {
        Client client = findClient(companyId, clientId);
        ClientMapper.updateEntityFromDTO(dto, client);
        addressService.updateClientAddresses(client, dto.getAddressList(), client.getCompany());
        return ClientMapper.toClientListResponseDTO(clientRepository.save(client));
    }

    @Transactional
    public void deleteClient(UUID companyId, UUID clientId) {
        Client client = findClient(companyId, clientId);
        deactivateClient(client);
        clientRepository.save(client);
    }
}