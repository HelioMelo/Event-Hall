package com.eventhall.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "team_functions")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class TeamFunction extends EntityBase {

    @NotEmpty(message = "O nome da função é obrigatório")
    @Size(max = 50, message = "O nome da função deve ter no máximo 50 caracteres")
    @Column(nullable = false, unique = true, length = 50)
    private String name;
}
