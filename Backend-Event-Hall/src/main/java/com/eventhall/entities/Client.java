package com.eventhall.entities;


import java.util.HashSet;
import java.util.Set;

import com.eventhall.entities.enums.ContactTypeEnum;
import com.eventhall.entities.enums.DocumentTypeEnum;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

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
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "clients")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
//@Where(clause = "is_active = true")
public class Client extends EntityBase {

	@NotEmpty(message = "O nome do cliente é obrigatória")
	@Size(max = 50, message = "O nome do cliente deve ter no máximo 50 caracteres")
	@Column(name = "client_name", length = 50, nullable = false)
	@JsonProperty
	private String name;

	@NotNull (message = "Tipo de documento é obrigatório")
	@Enumerated(EnumType.STRING)
	@Column(name = "document_type", nullable = false)
	@JsonProperty
	private DocumentTypeEnum documentType;

	@NotEmpty(message = "Documento é obrigatório")
	@Size(max = 14, message = "Documento deve ter no máximo 14 caracteres")
	@Size(min = 11, message = "Documento deve ter no mínimo 11 caracteres")
	@Column(name = "document", length = 14, nullable = false, unique = true)
	@JsonProperty
	private String document;

	@NotEmpty(message = "Contato é obrigatório")
	@Size(max = 11, message = "Contato deve ter no máximo 11 caracteres")
	@Size(min = 8, message = "Contato deve ter no mínimo 8 caracteres")
	@Column(name = "contact", length = 11, nullable = false)
	@JsonProperty
	private String contact;
	
    @Column(name = "contact_type", length = 11)
    @JsonProperty
    @Enumerated(EnumType.STRING)
    @NotNull (message = "Tipo de contato é obrigatório")
    private ContactTypeEnum contactType;

	@Size(max = 255, message = "URL do logo deve ter no máximo 255 caracteres")
	@Column(name = "logo_url", length = 255, nullable = true)
	@JsonProperty
	private String logoUrl;

	@NotEmpty(message = "Email é obrigatório")
	@Email(message = "Email deve ser válido")
	@Size(max = 100, message = "Email deve ter no máximo 100 caracteres")
	@Column(name = "email", length = 100, nullable = false, unique = true)
	@JsonProperty
	private String email;


	@OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
	@JoinColumn(name = "client_id")
	@JsonManagedReference
	private Set<Address> addressList = new HashSet<>();


	
	@ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "company_id", nullable = false)
    @JsonIgnore
    private Companies company;



}