package com.eventhall.entities;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import com.eventhall.entities.enums.ContractStatusEnum;
import com.eventhall.entities.enums.EventPackageEnum;
import com.eventhall.entities.enums.EventTypeEnum;

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
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "events")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Event extends EntityBase {

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "client_id", nullable = false)
    private Client client;

    @NotNull
    @Size(max = 100)
    @Column(nullable = false, length = 100)
    private String title;

    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(name = "event_type", nullable = false)
    private EventTypeEnum eventType;

    @Column(name = "venue")
    private String venue;

    @NotNull
    @Column(name = "start_date", nullable = false)
    private LocalDate startDate;

    @Column(name = "end_date")
    private LocalDate endDate;

    @Enumerated(EnumType.STRING)
    @Column(name = "status")
    private ContractStatusEnum status;

    @Column(name = "guests")
    private Integer guests;

    @Enumerated(EnumType.STRING)
    @Column(name = "event_package")
    private EventPackageEnum eventPackage;

    @Column(columnDefinition = "TEXT")
    private String notes;

    @OneToMany(mappedBy = "event", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Contract> contracts = new ArrayList<>();

    @OneToMany(mappedBy = "event", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<TeamAllocation> allocations = new ArrayList<>();
}
