package com.eventhall.entities;

import jakarta.persistence.Embeddable;

import java.math.BigDecimal;

import jakarta.persistence.Column;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Embeddable
public class PaymentOption {

    @Column(length = 100, nullable = false)
    private String title;

    @Column(length = 50, nullable = false)
    private String type;

    @Column(name = "min_entry_percent", precision = 5, scale = 2)
    private BigDecimal minEntryPercent;

    @Column(name = "max_installments")
    private Integer maxInstallments;

    @Column(length = 500)
    private String description;

    public PaymentOption() {}

    public PaymentOption(String title, String type, BigDecimal minEntryPercent, Integer maxInstallments, String description) {
        this.title = title;
        this.type = type;
        this.minEntryPercent = minEntryPercent;
        this.maxInstallments = maxInstallments;
        this.description = description;
    }

}
