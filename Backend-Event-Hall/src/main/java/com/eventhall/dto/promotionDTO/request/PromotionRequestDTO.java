package com.eventhall.dto.promotionDTO.request;

import com.eventhall.entities.GuestValue;
import com.eventhall.entities.PaymentOption;
import com.eventhall.entities.enums.PromotionTypeEnum;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.Instant;
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

    @DecimalMin(value = "0.0", inclusive = true, message = "Extra guest value must be positive")
    private BigDecimal extraGuestValue;

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

    private List<GuestValue> guestValues;

    private List<String> includedItems;

    @Size(max = 500)
    private String notes;

    private List<PaymentOption> paymentOptions;
    private Boolean isActive;
    private Instant createdAt;
    private Instant updatedAt;
}
