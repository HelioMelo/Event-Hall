package com.eventhall.service.promotionService;

import com.eventhall.dto.promotionDTO.request.PromotionRequestDTO;
import com.eventhall.dto.promotionDTO.response.PromotionResponseDTO;
import com.eventhall.entities.Companies;
import com.eventhall.entities.Promotion;
import com.eventhall.mappers.promotionMapper.PromotionMapper;
import com.eventhall.repository.CompaniesRepository;
import com.eventhall.repository.PromotionRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class PromotionServiceImpl implements IPromotionService {

    private final PromotionRepository promotionRepository;
    private final CompaniesRepository companiesRepository;
    private final PromotionMapper promotionMapper;

    private Companies getCompany(UUID companyId) {
        return companiesRepository.findById(companyId)
                .orElseThrow(() -> new EntityNotFoundException("Company not found with id: " + companyId));
    }

    @Override
    @Transactional
    public PromotionResponseDTO createPromotion(UUID companyId, PromotionRequestDTO dto) {
        Companies company = getCompany(companyId);

        if (promotionRepository.existsByCodeAndCompanyId(dto.getCode(), companyId)) {
            throw new IllegalArgumentException("Promotion code already exists for this company: " + dto.getCode());
        }

        Promotion promotion = promotionMapper.toEntity(dto);
        promotion.setCompany(company);
        Promotion saved = promotionRepository.save(promotion);
        return promotionMapper.toResponseDTO(saved);
    }

    @Override
    @Transactional
    public PromotionResponseDTO updatePromotion(UUID companyId, UUID promotionId, PromotionRequestDTO dto) {
        getCompany(companyId);
        Promotion existing = promotionRepository.findByIdAndCompanyId(promotionId, companyId)
                .orElseThrow(() -> new EntityNotFoundException("Promotion not found with id: " + promotionId));

        promotionMapper.updateEntityFromDTO(dto, existing);
        Promotion updated = promotionRepository.save(existing);
        return promotionMapper.toResponseDTO(updated);
    }

    @Override
    @Transactional
    public void deletePromotion(UUID companyId, UUID promotionId) {
        getCompany(companyId);
        Promotion existing = promotionRepository.findByIdAndCompanyId(promotionId, companyId)
                .orElseThrow(() -> new EntityNotFoundException("Promotion not found with id: " + promotionId));
        promotionRepository.delete(existing);
    }

    @Override
    @Transactional(readOnly = true)
    public PromotionResponseDTO getPromotionById(UUID companyId, UUID promotionId) {
        getCompany(companyId);
        Promotion promotion = promotionRepository.findByIdAndCompanyId(promotionId, companyId)
                .orElseThrow(() -> new EntityNotFoundException("Promotion not found with id: " + promotionId));
        return promotionMapper.toResponseDTO(promotion);
    }

    @Override
    @Transactional(readOnly = true)
    public List<PromotionResponseDTO> getAllPromotions(UUID companyId) {
        getCompany(companyId);
        List<Promotion> promotions = promotionRepository.findAllByCompanyId(companyId);
        return promotionMapper.toResponseList(promotions);
    }
}
