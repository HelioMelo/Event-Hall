package com.eventhall.mappers.promotionMapper;


import com.eventhall.dto.promotionDTO.request.PromotionRequestDTO;
import com.eventhall.dto.promotionDTO.response.PromotionResponseDTO;
import com.eventhall.entities.Promotion;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Component
public class PromotionMapper {

    // 🔹 Converte RequestDTO → Entity
    public Promotion toEntity(PromotionRequestDTO dto) {
        if (dto == null) {
            return null;
        }

        Promotion promotion = new Promotion();
        promotion.setName(dto.getName());
        promotion.setCode(dto.getCode());
        promotion.setDescription(dto.getDescription());
        promotion.setStartDate(dto.getStartDate());
        promotion.setEndDate(dto.getEndDate());
        promotion.setType(dto.getType());
        promotion.setBaseValue(dto.getBaseValue());
        promotion.setDurationHours(dto.getDurationHours());
        promotion.setIncludedItems(dto.getIncludedItems());
        promotion.setNotes(dto.getNotes());
        promotion.setIsActive(dto.getIsActive() != null ? dto.getIsActive() : true);
        return promotion;
    }

    // 🔹 Converte Entity → ResponseDTO
    public PromotionResponseDTO toResponseDTO(Promotion entity) {
        if (entity == null) {
            return null;
        }

        PromotionResponseDTO dto = new PromotionResponseDTO();
        dto.setId(entity.getId() != null ? entity.getId() : UUID.randomUUID());
        dto.setName(entity.getName());
        dto.setCode(entity.getCode());
        dto.setDescription(entity.getDescription());
        dto.setStartDate(entity.getStartDate());
        dto.setEndDate(entity.getEndDate());
        dto.setType(entity.getType());
        dto.setBaseValue(entity.getBaseValue());
        dto.setDurationHours(entity.getDurationHours());
        dto.setIncludedItems(entity.getIncludedItems());
        dto.setNotes(entity.getNotes());
        dto.setIsActive(entity.getIsActive());
        dto.setCreatedAt(entity.getCreatedAt());
        dto.setUpdatedAt(entity.getUpdatedAt());
        return dto;
    }

    // 🔹 Converte Lista<Entity> → Lista<ResponseDTO>
    public List<PromotionResponseDTO> toResponseList(List<Promotion> promotions) {
        if (promotions == null) {
            return null;
        }
        return promotions.stream()
                .map(this::toResponseDTO)
                .collect(Collectors.toList());
    }

    // 🔹 Atualiza uma entidade existente com dados de um DTO (ignora nulos)
    public void updateEntityFromDTO(PromotionRequestDTO dto, Promotion entity) {
        if (dto == null || entity == null) {
            return;
        }

        if (dto.getName() != null) entity.setName(dto.getName());
        if (dto.getCode() != null) entity.setCode(dto.getCode());
        if (dto.getDescription() != null) entity.setDescription(dto.getDescription());
        if (dto.getStartDate() != null) entity.setStartDate(dto.getStartDate());
        if (dto.getEndDate() != null) entity.setEndDate(dto.getEndDate());
        if (dto.getType() != null) entity.setType(dto.getType());
        if (dto.getBaseValue() != null) entity.setBaseValue(dto.getBaseValue());
        if (dto.getDurationHours() != null) entity.setDurationHours(dto.getDurationHours());
        if (dto.getIncludedItems() != null) entity.setIncludedItems(dto.getIncludedItems());
        if (dto.getNotes() != null) entity.setNotes(dto.getNotes());
        if (dto.getIsActive() != null) entity.setIsActive(dto.getIsActive());
    }
}
