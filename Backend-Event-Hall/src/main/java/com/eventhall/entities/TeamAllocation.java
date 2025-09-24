package com.eventhall.entities;

import java.time.LocalDate;
import java.time.LocalTime;
import java.math.BigDecimal;

import com.eventhall.entities.enums.AllocationStatusEnum;
import com.eventhall.entities.enums.PaymentTypeEnum;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.Column;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "team_allocations")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class TeamAllocation extends EntityBase {

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "event_id", nullable = false)
    private Event event;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id", nullable = false)
    private TeamMember member;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "function_id")
    private TeamFunction function;

    @Column(nullable = false)
    private LocalDate date;

    @Column
    private LocalTime startTime;

    @Column
    private LocalTime endTime;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private PaymentTypeEnum paymentType;

    @Column(nullable = false, precision = 10, scale = 2)
    private BigDecimal agreedValue;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private AllocationStatusEnum status = AllocationStatusEnum.PENDING;
}
