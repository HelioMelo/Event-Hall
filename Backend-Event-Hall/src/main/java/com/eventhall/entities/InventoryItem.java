package com.eventhall.entities;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "inventory_items")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class InventoryItem extends EntityBase {

    @Column(nullable = false, length = 100)
    private String name;

    @Column(length = 50)
    private String category;

    @Column(length = 20)
    private String unit;

    @Column(name = "stock_quantity")
    private Integer stock;

    @Column(name = "min_stock")
    private Integer minStock;

    @Column(name = "average_cost", precision = 10, scale = 2)
    private BigDecimal averageCost;

    @ManyToOne
    @JoinColumn(name = "main_supplier_id")
    private Supplier mainSupplier;

    @OneToMany(mappedBy = "item", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<StockMovement> movements = new ArrayList<>();
}
