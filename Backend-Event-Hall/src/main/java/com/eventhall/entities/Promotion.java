package com.eventhall.entities;

import com.eventhall.entities.enums.PromotionTypeEnum;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "promotion")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Promotion extends EntityBase {

    @Column(length = 100, nullable = false)
    private String name;

    @Column(length = 50, nullable = false, unique = true)
    private String code;

    @Column(length = 500)
    private String description;

    @Column(name = "extra_guest_value", precision = 10, scale = 2)
    private BigDecimal extraGuestValue;

    @Column(name = "start_date", nullable = false)
    private LocalDate startDate;

    @Column(name = "end_date", nullable = false)
    private LocalDate endDate;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private PromotionTypeEnum type;

    @Column(name = "base_value", precision = 15, scale = 2)
    private BigDecimal baseValue;

    @Column(name = "duration_hours")
    private Integer durationHours;

    @ElementCollection
    @CollectionTable(
            name = "promotion_guest_values",
            joinColumns = @JoinColumn(name = "promotion_id")
    )
    private List<GuestValue> guestValues;

    @ElementCollection
    @CollectionTable(
            name = "promotion_items",
            joinColumns = @JoinColumn(name = "promotion_id")
    )
    @Column(name = "item", length = 255)
    private List<String> includedItems;

    @Column(length = 500)
    private String notes;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "company_id", nullable = false)
    @JsonIgnore
    private Companies company;

    @ElementCollection
    @CollectionTable(
            name = "promotion_payment_options",
            joinColumns = @JoinColumn(name = "promotion_id")
    )
    private List<PaymentOption> paymentOptions;
}
