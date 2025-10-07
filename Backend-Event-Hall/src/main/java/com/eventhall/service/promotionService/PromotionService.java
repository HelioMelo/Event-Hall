package com.eventhall.service.promotionService;

import com.eventhall.dto.promotionDTO.request.PromotionRequestDTO;
import com.eventhall.dto.promotionDTO.response.PromotionResponseDTO;
import com.eventhall.entities.Companies;
import com.eventhall.entities.Promotion;
import com.eventhall.mappers.promotionMapper.PromotionMapper;
import com.eventhall.repository.CompaniesRepository;
import com.eventhall.repository.PromotionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
@Service
@RequiredArgsConstructor
public class PromotionService implements IPromotionService {

    private final PromotionRepository promotionRepository;
    private final CompaniesRepository companiesRepository;
    private final PromotionMapper promotionMapper = new PromotionMapper();

    private Companies getCompany(UUID companyId) {
        return companiesRepository.findById(companyId)
                .orElseThrow(() -> new RuntimeException("Company not found with id: " + companyId));
    }

    @Override
    public PromotionResponseDTO createPromotion(UUID companyId, PromotionRequestDTO dto) {
        Companies company = getCompany(companyId);
        Promotion promotion = promotionMapper.toEntity(dto);
        promotion.setCompany(company);
        return promotionMapper.toResponseDTO(promotionRepository.save(promotion));
    }

    @Override
    public PromotionResponseDTO updatePromotion(UUID companyId, UUID promotionId, PromotionRequestDTO dto) {
        getCompany(companyId);
        Promotion existing = promotionRepository.findByIdAndCompanyId(promotionId, companyId)
                .orElseThrow(() -> new RuntimeException("Promotion not found"));
        promotionMapper.updateEntityFromDTO(dto, existing);
        return promotionMapper.toResponseDTO(promotionRepository.save(existing));
    }

    @Override
    public List<PromotionResponseDTO> getAllPromotions(UUID companyId) {
        getCompany(companyId);
        return promotionMapper.toResponseList(promotionRepository.findAllByCompanyId(companyId));
    }

    @Override
    public PromotionResponseDTO getPromotionById(UUID companyId, UUID promotionId) {
        getCompany(companyId);
        Promotion entity = promotionRepository.findByIdAndCompanyId(promotionId, companyId)
                .orElseThrow(() -> new RuntimeException("Promotion not found"));
        return promotionMapper.toResponseDTO(entity);
    }

    @Override
    public void deletePromotion(UUID companyId, UUID promotionId) {
        getCompany(companyId);
        Promotion entity = promotionRepository.findByIdAndCompanyId(promotionId, companyId)
                .orElseThrow(() -> new RuntimeException("Promotion not found"));
        promotionRepository.delete(entity);
    }
}
