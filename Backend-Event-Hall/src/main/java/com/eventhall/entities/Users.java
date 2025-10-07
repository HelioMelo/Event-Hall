package com.eventhall.entities;


import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.eventhall.entities.enums.ContactTypeEnum;
import com.eventhall.entities.enums.UserTypeEnum;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnore;
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
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;



@Entity
@Table(name = "users")
@Getter
@Setter
@AllArgsConstructor

@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
public class Users extends EntityBase implements UserDetails {

	private static final long serialVersionUID = 1L;
	
	@NotEmpty(message = "O nome do usário é obrigatório")
    @Size(max = 50, message = "O nome do usuário deve ter no máximo 50 caracteres")
    @Column(name = "user_name", length = 50, nullable = false)
	@JsonProperty
	private String name;
    
    @NotEmpty(message = "O email é obrigatório")
    @Email(message = "O email deve ser válido")
    @Size(max = 100, message = "O email deve ter no máximo 100 caracteres")
    @Column(name = "email", length = 100, nullable = false, unique = true)
    @JsonProperty
    private String email;

    @Size(max = 11, message = "Contato deve ter no máximo 11 caracteres")
    @Column(name = "contact", length = 11)
    @JsonProperty
    private String contact;
    
    @Column(name = "contact_type", length = 11)
    @JsonProperty
    @Enumerated(EnumType.STRING)
    private ContactTypeEnum contactType;
    
    @Column(name = "job_timer", length = 11, precision = 8, scale = 2)
    @JsonProperty
    private BigDecimal jobTimer;

    @NotEmpty(message = "A senha é obrigatório")
    @Size(min = 10, message = "A senha deve ter no mínimo 10 caracteres")
    @Column(name = "password", length = 150, nullable = false)
    @JsonProperty
    private String password;

    @NotNull(message = "O tipo de usuário é obrigatório")
    @Column(name = "user_type", nullable = false)
    @Enumerated(EnumType.STRING)
    @JsonProperty
    private UserTypeEnum userType;

    @Size(max = 255, message = "A URL da foto deve ter no máximo 255 caracteres")
    @Column(name = "photo_url", length = 255, nullable = true)
    @JsonProperty
    private String photoUrl;
   
    
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY,  mappedBy = "user")
    private List<Address> address = new ArrayList<>();
    

	@ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "company_id", nullable = false)
    @JsonIgnore
    private Companies company;
	
    @OneToOne(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    private UserSettings settings;
    
	public Users(String email, String password, String name, Companies company, UserTypeEnum userType) {			
		this.email = email;
		this.password = password;
		this.userType = userType;
		this.name = name;
		this.company = company;
	}
	
	public Users() {}
	
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		if (this.userType == UserTypeEnum.ADMIN)
			return List.of(new SimpleGrantedAuthority("ROLE_ADMIN"), new SimpleGrantedAuthority("ROLE_TECHNICIAN"));
		else
			return List.of(new SimpleGrantedAuthority("ROLE_TECHNICIAN"));
	}

	@Override
	public String getUsername() {
		return email;
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return true;
	}

	@Override
	public String getPassword() {
		return password;
	}

}