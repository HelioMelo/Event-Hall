package com.eventhall.entities;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import com.eventhall.entities.enums.ContractStatusEnum;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "contracts")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Contract extends EntityBase {

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "event_id", nullable = false)
    private Event event;

    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(name = "status", nullable = false)
    private ContractStatusEnum status;

    @Column(name = "total_value", precision = 15, scale = 2)
    private BigDecimal totalValue;

    @NotNull
    @Column(name = "created_date", nullable = false, updatable = false)
    private LocalDate createdDate;

    @Column(name = "signing_date")
    @FutureOrPresent(message = "Data de assinatura deve ser hoje ou no futuro")
    private LocalDate signingDate;

    @Column(name = "expiration_date")
    @Future(message = "Data de expiração deve ser no futuro")
    private LocalDate expirationDate;

    @Column(name = "template_id")
    private String templateId;

    @Column(columnDefinition = "TEXT")
    private String description;

    @OneToMany(mappedBy = "contract", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Receivable> receivables = new ArrayList<>();
}
