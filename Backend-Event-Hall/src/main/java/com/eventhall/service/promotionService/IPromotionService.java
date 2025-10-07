package com.eventhall.service.promotionService;

import com.eventhall.dto.promotionDTO.request.PromotionRequestDTO;
import com.eventhall.dto.promotionDTO.response.PromotionResponseDTO;

import java.util.List;
import java.util.UUID;

public interface IPromotionService {

    PromotionResponseDTO createPromotion(UUID companyId, PromotionRequestDTO dto);

    PromotionResponseDTO updatePromotion(UUID companyId, UUID promotionId, PromotionRequestDTO dto);

    void deletePromotion(UUID companyId, UUID promotionId);

    PromotionResponseDTO getPromotionById(UUID companyId, UUID promotionId);

    List<PromotionResponseDTO> getAllPromotions(UUID companyId);
}
