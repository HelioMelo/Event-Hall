package com.eventhall.entities;


import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;



@Table(name = "address")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
@Entity
public class Address extends EntityBase {
    
	 @NotEmpty(message = "Bairro é obrigatório")
	    @Size(max = 150, message = "Bairro deve ter no máximo 150 caracteres")
	    @Column(name = "neighborhood", length = 150, nullable = false)
	    @JsonProperty
	    private String neighborhood;

	    @NotEmpty(message = "CEP é obrigatório")
	    @Size(min = 8, max = 8, message = "CEP deve ter exatamente 8 caracteres")
	    @Column(name = "zipCode", length = 8, nullable = false)
	    @JsonProperty
	    private String zipCode;

	    @NotEmpty(message = "Rua é obrigatório")
	    @Size(max = 150, message = "Rua deve ter no máximo 150 caracteres")
	    @Column(name = "street", length = 150, nullable = false)
	    @JsonProperty
	    private String street;

	    @NotEmpty(message = "Cidade é obrigatório")
	    @Size(max = 40, message = "Cidade deve ter no máximo 40 caracteres")
	    @Column(name = "city", length = 40, nullable = false)
	    @JsonProperty
	    private String city;

	    @NotEmpty(message = "Estado é obrigatório")
	    @Size(max = 150, message = "Estado deve ter no máximo 150 caracteres")
	    @Column(name = "state", length = 150, nullable = false)
	    @JsonProperty
	    private String state;

	    @Size(max = 100, message = "Complemento deve ter no máximo 100 caracteres")
	    @Column(name = "complement", length = 100, nullable = true)
	    @JsonProperty
	    private String complement;


	   	@Column(name = "number", length = 10, nullable = true)
	   	@JsonProperty
	   	private String number;
	        
		@ManyToOne(fetch = FetchType.LAZY)
	    @JoinColumn(name = "user_id")
		@JsonBackReference
		private Users user;
		
		@ManyToOne(fetch = FetchType.LAZY)
	    @JoinColumn(name = "client_id")
	    @JsonBackReference
	    private Client client;
		
		@ManyToOne(fetch = FetchType.LAZY)
	    @JoinColumn(name = "company_id", nullable = false)
	    @JsonIgnore
	    @JsonBackReference
	    private Companies company;


	
  }