package com.eventhall.entities;



import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

import com.eventhall.dto.request.RegisterDTO;
import com.eventhall.entities.enums.ContactTypeEnum;
import com.eventhall.entities.enums.DocumentTypeEnum;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "companies")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
public class Companies extends EntityBase {

    @NotEmpty(message = "O nome da empresa é obrigatório")
    @Size(max = 50, message = "O nome do empresa deve ter no máximo 50 caracteres")
    @Column(name = "name", length = 50, nullable = false)
    @JsonProperty
    private String name;
    
    @NotNull (message = "Tipo de documento é obrigatório")
    @Enumerated(EnumType.STRING)
    @Column(name = "document_type", nullable = false)
    @JsonProperty
    private DocumentTypeEnum documentType;

    @NotEmpty(message = "Documento é obrigatório")
    @Size(min = 14, message = "Documento deve ter 14 caracteres")
    @Column(name = "document", length = 14, nullable = false, unique = true)
    @JsonProperty
    private String document;

    @Size(max = 11, message = "Contato deve ter no máximo 11 caracteres")
    @Size(min = 8, message = "Contato deve ter no mínimo 8 caracteres")
    @Column(name = "contact", length = 11)
    @JsonProperty
    private String contact;
    
    @Column(name = "contact_type")
    @Enumerated(EnumType.STRING)
    @JsonProperty
    private ContactTypeEnum contactType;

    @Size(max = 255, message = "URL do logo deve ter no máximo 255 caracteres")
    @Column(name = "logo_url", length = 255, nullable = true)
    @JsonProperty
    private String slug;
    
	@Column(name = "terms_of_user")
	@JsonProperty
	private Boolean termsOfUser = false;
	
	@Column(name = "payment_active")
	@JsonProperty
	private Boolean paymentActive = false;
	
    @NotEmpty(message = "O nome do usuário é obrigatório")
    @Size(max = 50, message = "O nome do usuário deve ter no máximo 50 caracteres")
    @Column(name = "created_by_user", length = 50, nullable = false)
    @JsonProperty
    private String createdByUser;
    
	@Column(name = "actived_at")
	@JsonProperty
	private Instant activedAt = null; 

    
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY,  mappedBy = "company")
    private List<Address> address = new ArrayList<>();
    
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY,  mappedBy = "company")
    private List<Client> clients = new ArrayList<>();
    
    
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY,  mappedBy = "company")
    private List<Users> users = new ArrayList<>();

    public Companies registerCompanyForUnauthenticatedUser(RegisterDTO registerDto){
		Companies newCompany = new Companies();
		newCompany.name = registerDto.nameCompany();
		newCompany.document = registerDto.documentCompany();
		newCompany.documentType = DocumentTypeEnum.CNPJ;
		newCompany.createdByUser = registerDto.nameUser();
		
		return newCompany;
    }


}