package com.eventhall.service.clientService;

import com.eventhall.dto.clientDTO.request.ClientRequestDTO;
import com.eventhall.entities.Client;
import com.eventhall.entities.Companies;
import com.eventhall.mappers.clientMapper.ClientMapper;
import com.eventhall.repository.ClientRepository;
import com.eventhall.repository.CompaniesRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ClientService {

    private final CompaniesRepository companiesRepository;
    private final ClientRepository clientRepository;

    /**
     * Cria um novo cliente para a empresa especificada.
     * @param companyId UUID da empresa (usado para associar o cliente)
     */
    @Transactional
    public ClientRequestDTO createClient(UUID companyId, ClientRequestDTO clientRequestDTO) {

        Companies company = getCompany(companyId);
        System.out.println(company);

        Client client = ClientMapper.toEntity(clientRequestDTO);
        client.setCompany(company);

        if (client.getAddressList() != null) {
            client.getAddressList().forEach(address -> {
                address.setClient(client);
                address.setCompany(company);
            });
        }

        Client savedClient = clientRepository.save(client);
        return ClientMapper.toDTO(savedClient);
    }


    /**
     * Busca empresa pelo ID
     */
    public Companies getCompany(UUID companyId) {
        return companiesRepository.findById(companyId)
                .orElseThrow(() -> new IllegalStateException("Empresa não encontrada ou não logada"));
    }
}
