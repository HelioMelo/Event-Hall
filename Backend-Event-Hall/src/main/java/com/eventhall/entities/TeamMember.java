package com.eventhall.entities;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.math.BigDecimal;

import com.eventhall.entities.enums.PaymentTypeEnum;
import com.eventhall.entities.enums.TeamStatusEnum;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.PastOrPresent;
import jakarta.validation.constraints.Size;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "team_members")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class TeamMember extends EntityBase {

    @NotEmpty(message = "O nome do membro é obrigatório")
    @Size(max = 50, message = "O nome do membro deve ter no máximo 50 caracteres")
    @Column(nullable = false, length = 50)
    private String name;

    @Size(max = 14, message = "CPF/CNPJ deve ter no máximo 14 caracteres")
    private String cpfCnpj;


    @Size(max = 100, message = "Email deve ter no máximo 100 caracteres")
    @Email(message = "Email deve ser válido")
    private String email;

  
    @Size(max = 15, message = "Telefone deve ter no máximo 15 caracteres")
    private String phone;


    @ManyToOne
    @JoinColumn(name = "function_id")
    private TeamFunction function;


    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private PaymentTypeEnum paymentType;


    @Column(precision = 10, scale = 2)
    private BigDecimal hourlyRate;

    @Column(precision = 10, scale = 2)
    private BigDecimal dailyRate;

    @Column(precision = 10, scale = 2)
    private BigDecimal pieceRate;


    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private TeamStatusEnum status;

    @PastOrPresent(message = "A data de contratação não pode ser no futuro")
    @Column(nullable = false)
    private LocalDate hireDate;

    @OneToMany(mappedBy = "member", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<TeamAllocation> allocations = new ArrayList<>();
}
