package com.eventhall.dto.promotionDTO.response;

import com.eventhall.entities.enums.PromotionTypeEnum;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.Instant;
import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PromotionResponseDTO {

    private UUID id;
    private String name;
    private String code;
    private String description;
    private LocalDate startDate;
    private LocalDate endDate;
    private PromotionTypeEnum type;
    private BigDecimal baseValue;
    private Integer durationHours;
    private List<String> includedItems;
    private String notes;
    private Boolean isActive;
    private Instant createdAt;
    private Instant updatedAt;
}
