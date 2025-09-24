package com.eventhall.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.eventhall.entities.Client;

@Repository
public interface ClientRepository extends JpaRepository<Client, UUID> {

}
