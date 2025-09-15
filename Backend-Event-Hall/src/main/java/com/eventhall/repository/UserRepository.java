package com.eventhall.repository;



import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;

import com.eventhall.entities.Users;


public interface UserRepository extends JpaRepository<Users, UUID> {
    UserDetails findByEmail(String login);
    boolean existsByIdAndCompany_Id(UUID userId, UUID companyId);


}