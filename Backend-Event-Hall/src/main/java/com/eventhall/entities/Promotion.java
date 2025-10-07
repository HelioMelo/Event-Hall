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

    @Column(length = 50, nullable = false)
    private String code;

    @Column(length = 500)
    private String description;

    private LocalDate startDate;
    private LocalDate endDate;

    @Enumerated(EnumType.STRING)
    private PromotionTypeEnum type;


    private BigDecimal baseValue;
    private Integer durationHours;

    @ElementCollection
    @CollectionTable(name = "promotion_items", joinColumns = @JoinColumn(name = "promotion_id"))
    @Column(name = "item")
    private List<String> includedItems;

    @Column(length = 500)
    private String notes;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "company_id", nullable = false)
    @JsonIgnore
    private Companies company;
}