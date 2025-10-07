package com.eventhall.service.addressService;

import com.eventhall.dto.addressDTO.request.AddressRequestDTO;
import com.eventhall.entities.Address;
import com.eventhall.entities.Client;
import com.eventhall.entities.Companies;
import com.eventhall.repository.ClientRepository;
import com.eventhall.repository.CompaniesRepository;
import com.eventhall.service.addressService.addressMatcher.AddressMatcher;
import com.eventhall.mappers.addressMapper.AddressMapper;
import com.eventhall.service.addressService.factory.AddressFactory;
import com.eventhall.service.clientService.ClientValidator;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Optional;
import java.util.Set;
import java.util.UUID;

@Component
@RequiredArgsConstructor
public class AddressUpdater {

    private final AddressMatcher addressMatcher;
    private final CompaniesRepository companiesRepository;
    private final ClientRepository clientRepository;
    private final ClientValidator clientValidator;

    // ====================== Métodos auxiliares ======================
    public Companies getActiveCompany(UUID companyId) {
        Companies company = companiesRepository.findById(companyId).orElse(null);
        clientValidator.validateCompanyExists(company, "Empresa não encontrada");
        return company;
    }

    public Client getActiveClient(UUID companyId, UUID clientId) {
        Client client = clientRepository.findByIdAndCompany_IdAndIsActiveTrue(clientId, companyId).orElse(null);
        clientValidator.validateClientExists(client);
        return client;
    }

    // ====================== Upsert ======================
    public Address upsertAddress(Client client, Companies company, AddressRequestDTO dto) {
        Optional<Address> existing = client.getAddressList().stream()
                .filter(a -> addressMatcher.matches(dto, a))
                .findFirst();

        if (existing.isPresent()) {
            Address address = existing.get();
            address.setIsActive(true);
            AddressMapper.updateEntityFromDTO(dto, address);
            return address;
        } else {
            Address address = AddressFactory.createAddress(client, company, dto);
            address.setIsActive(true);
            client.getAddressList().add(address);
            return address;
        }
    }

    // ====================== Atualização ======================
    public Address updateAddress(Client client, Companies company, Address addressToUpdate, AddressRequestDTO dto) {
        if (addressMatcher.matches(dto, addressToUpdate)) {
            AddressMapper.updateEntityFromDTO(dto, addressToUpdate);
            addressToUpdate.setIsActive(true);
            return addressToUpdate;
        } else {
            addressToUpdate.setIsActive(false);
            Address newAddress = AddressFactory.createAddress(client, company, dto);
            newAddress.setIsActive(true);
            client.getAddressList().add(newAddress);
            return newAddress;
        }
    }

    // ====================== Desativação de endereços não enviados ======================
    public void deactivateMissingAddresses(Client client, Set<AddressRequestDTO> dtos) {
        client.getAddressList().forEach(address -> {
            boolean found = dtos.stream().anyMatch(dto -> addressMatcher.matches(dto, address));
            if (!found) address.setIsActive(false);
        });
    }

    // ====================== Desativação individual ======================
    public void deactivateAddress(Client client, Address address) {
        address.setIsActive(false);
    }
}
