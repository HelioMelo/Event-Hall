package com.eventhall.dto.promotionDTO.request;

import com.eventhall.entities.enums.PromotionTypeEnum;
import jakarta.validation.constraints.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PromotionRequestDTO {

    @NotBlank(message = "Name is required")
    @Size(max = 100)
    private String name;

    @NotBlank(message = "Code is required")
    @Size(max = 50)
    private String code;

    @Size(max = 500)
    private String description;

    @NotNull(message = "Start date is required")
    private LocalDate startDate;

    @NotNull(message = "End date is required")
    private LocalDate endDate;

    @NotNull(message = "Type is required")
    private PromotionTypeEnum type;

    @DecimalMin(value = "0.0", inclusive = true, message = "Base value must be positive")
    private BigDecimal baseValue;

    @Min(value = 1, message = "Duration (hours) must be at least 1")
    private Integer durationHours;

    private List<String> includedItems;

    @Size(max = 500)
    private String notes;

    private Boolean isActive;
}
