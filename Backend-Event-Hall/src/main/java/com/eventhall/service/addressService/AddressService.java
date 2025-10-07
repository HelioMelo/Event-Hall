package com.eventhall.service.addressService;

import com.eventhall.dto.addressDTO.request.AddressRequestDTO;
import com.eventhall.dto.addressDTO.response.AddressResponseDTO;
import com.eventhall.entities.Address;
import com.eventhall.entities.Client;
import com.eventhall.entities.Companies;
import com.eventhall.mappers.addressMapper.AddressMapper;
import com.eventhall.repository.ClientRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Set;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class AddressService implements IAddressService {


    private final ClientRepository clientRepository;
    private final AddressUpdater addressUpdater;

    // ====================== Atualização de lista de endereços ======================
    @Override
    @Transactional
    public void updateClientAddresses(Client client, Set<AddressRequestDTO> addressDTOs, Companies company) {
        if (addressDTOs == null || addressDTOs.isEmpty()) return;

        addressDTOs.forEach(dto -> addressUpdater.upsertAddress(client, company, dto));
        addressUpdater.deactivateMissingAddresses(client, addressDTOs);

        clientRepository.save(client);
    }

    // ====================== Criação ou atualização individual ======================
    @Transactional
    public AddressResponseDTO createOrUpdateAddress(UUID companyId, UUID clientId, AddressRequestDTO dto) {
        Companies company = addressUpdater.getActiveCompany(companyId);
        Client client = addressUpdater.getActiveClient(companyId, clientId);

        Address address = addressUpdater.upsertAddress(client, company, dto);
        clientRepository.save(client);

        return AddressMapper.toResponseDTO(address);
    }

    @Transactional
    public AddressResponseDTO updateAddress(UUID companyId, UUID clientId, UUID addressId, AddressRequestDTO dto) {
        Companies company = addressUpdater.getActiveCompany(companyId);
        Client client = addressUpdater.getActiveClient(companyId, clientId);

        Address currentAddress = client.getAddressList().stream()
                .filter(a -> a.getId().equals(addressId) && Boolean.TRUE.equals(a.getIsActive()))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("Endereço não encontrado ou inativo"));

        Address updatedAddress = addressUpdater.updateAddress(client, company, currentAddress, dto);

        clientRepository.save(client);

        return AddressMapper.toResponseDTO(updatedAddress);
    }

    @Transactional
    public void deactivateAddress(UUID companyId, UUID clientId, UUID addressId) {
        Client client = addressUpdater.getActiveClient(companyId, clientId);
        Address address = client.getAddressList().stream()
                .filter(a -> a.getId().equals(addressId))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("Endereço não encontrado"));

        addressUpdater.deactivateAddress(client, address);
        clientRepository.save(client);
    }


}
