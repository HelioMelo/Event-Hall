package com.eventhall.entities;

import java.math.BigDecimal;

import com.eventhall.entities.enums.TeamStatusEnum;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "suppliers")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Supplier extends EntityBase {

    @Column(nullable = false)
    private String name;

    @Column(length = 100)
    private String category;

    @Column(length = 14)
    private String cnpj;

    @Column(length = 50)
    private String contact;

    @Column(length = 20)
    private String phone;

    @Column(length = 100)
    private String email;

    @Column(columnDefinition = "TEXT")
    private String address;

    @Column(columnDefinition = "TEXT")
    private String paymentTerms;

    @Column(precision = 5, scale = 2)
    private BigDecimal rating;

    @Enumerated(EnumType.STRING)
    @Column(name = "status")
    private TeamStatusEnum status;

}
