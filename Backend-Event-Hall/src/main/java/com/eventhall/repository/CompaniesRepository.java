package com.eventhall.repository;



import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.eventhall.entities.Companies;




public interface CompaniesRepository extends JpaRepository<Companies, UUID>  {
    boolean existsByDocument(String document);
}