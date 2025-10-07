package com.eventhall.repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.eventhall.entities.Client;

@Repository
public interface ClientRepository extends JpaRepository<Client, UUID> {

    List<Client> findByCompany_IdAndIsActiveTrue(UUID companyId);

    Optional<Client> findByIdAndCompany_IdAndIsActiveTrue(UUID clientId, UUID companyId);

    Optional<Client> findByDocumentAndCompany_Id(String document, UUID companyId);
}
