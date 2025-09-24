package com.eventhall.entities;

import java.time.LocalDate;
import java.math.BigDecimal;

import com.eventhall.entities.enums.StockMovementTypeEnum;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "stock_movements")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class StockMovement extends EntityBase {

    @ManyToOne
    @JoinColumn(name = "item_id", nullable = false)
    private InventoryItem item;

    @Enumerated(EnumType.STRING)
    @Column(name = "movement_type", nullable = false)
    private StockMovementTypeEnum movementType;

    @Column(nullable = false)
    private Integer quantity;

    @Column(name = "unit_cost", precision = 10, scale = 2)
    private BigDecimal unitCost;

    @Column(length = 255)
    private String reason;

    @Column(name = "movement_date")
    private LocalDate date;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private Users user;
}
