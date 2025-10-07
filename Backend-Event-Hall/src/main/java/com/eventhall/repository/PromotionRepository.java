package com.eventhall.repository;

import com.eventhall.entities.Promotion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface PromotionRepository extends JpaRepository<Promotion, UUID> {
    boolean existsByCodeAndCompanyId(String code, UUID companyId);
    List<Promotion> findAllByCompanyId(UUID companyId);
    Optional<Promotion> findByIdAndCompanyId(UUID id, UUID companyId);

}
