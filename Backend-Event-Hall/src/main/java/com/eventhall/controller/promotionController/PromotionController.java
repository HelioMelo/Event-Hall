package com.eventhall.controller.promotionController;

import com.eventhall.dto.promotionDTO.request.PromotionRequestDTO;
import com.eventhall.dto.promotionDTO.response.PromotionResponseDTO;
import com.eventhall.service.promotionService.IPromotionService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/promotions")
@RequiredArgsConstructor
public class PromotionController {

    private final IPromotionService promotionService;

    @PostMapping("/companies/{companyId}/promotions")
    public ResponseEntity<PromotionResponseDTO> create(@PathVariable UUID companyId,
                                                       @RequestBody PromotionRequestDTO dto) {
        PromotionResponseDTO created = promotionService.createPromotion(companyId, dto);
        return ResponseEntity.ok(created);
    }

    @PutMapping("/companies/{companyId}/promotions/{id}")
    public ResponseEntity<PromotionResponseDTO> update(@PathVariable UUID companyId,
                                                       @PathVariable UUID id,
                                                       @RequestBody PromotionRequestDTO dto) {
        PromotionResponseDTO updated = promotionService.updatePromotion(companyId, id, dto);
        return ResponseEntity.ok(updated);
    }

    @GetMapping("/companies/{companyId}/promotions/{id}")
    public ResponseEntity<PromotionResponseDTO> findById(@PathVariable UUID companyId,
                                                         @PathVariable UUID id) {
        PromotionResponseDTO promotion = promotionService.getPromotionById(companyId, id);
        return ResponseEntity.ok(promotion);
    }

    @GetMapping("/companies/{companyId}/promotions")
    public ResponseEntity<List<PromotionResponseDTO>> findAll(@PathVariable UUID companyId) {
        List<PromotionResponseDTO> promotions = promotionService.getAllPromotions(companyId);
        return ResponseEntity.ok(promotions);
    }

    @DeleteMapping("/companies/{companyId}/promotions/{id}")
    public ResponseEntity<Void> delete(@PathVariable UUID companyId,
                                       @PathVariable UUID id) {
        promotionService.deletePromotion(companyId, id);
        return ResponseEntity.noContent().build();
    }
}