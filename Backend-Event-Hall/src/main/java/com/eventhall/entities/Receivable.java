package com.eventhall.entities;

import java.math.BigDecimal;
import java.time.LocalDate;

import com.eventhall.entities.enums.PaymentStatusEnum;
import com.eventhall.entities.enums.ReceivableTypeEnum;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "receivables")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Receivable extends EntityBase {

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "contract_id", nullable = false)
    private Contract contract;

    @NotNull
    @Enumerated(EnumType.STRING)
    private ReceivableTypeEnum type;

    @Column(columnDefinition = "TEXT")
    private String description;

    @NotNull
    @Column(nullable = false)
    private BigDecimal value;

    @NotNull
    @Column(nullable = false)
    private LocalDate dueDate;

    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private PaymentStatusEnum paymentStatus;

    private LocalDate paymentDate;

    @Column(length = 50)
    private String paymentMethod;
}
