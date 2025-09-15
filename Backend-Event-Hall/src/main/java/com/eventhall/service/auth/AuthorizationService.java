package com.eventhall.service.auth;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestBody;

import com.eventhall.config.security.TokenService;
import com.eventhall.dto.request.LoginDTO;
import com.eventhall.dto.request.RegisterDTO;
import com.eventhall.dto.response.LoginResponseDTO;
import com.eventhall.entities.Companies;
import com.eventhall.entities.Users;
import com.eventhall.entities.enums.UserTypeEnum;
import com.eventhall.repository.CompaniesRepository;
import com.eventhall.repository.UserRepository;

import jakarta.validation.Valid;



//classe que o spring vai buscar para validar as autenticações 
@Service
public class AuthorizationService implements UserDetailsService {
	
	@Autowired
	private ApplicationContext applicationContext;
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private CompaniesRepository companiesRepository;
	
	
	@Autowired
	private TokenService tokenService;
	

	private AuthenticationManager authenticationManager;
	
 
	@Override //metodo para consultar após o usuário fazer autenticação!
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		return userRepository.findByEmail(username);
	}
	
	public ResponseEntity<Object> login(@RequestBody @Valid LoginDTO loginDto){

			authenticationManager = applicationContext.getBean(AuthenticationManager.class);
			var usernamePassword = new UsernamePasswordAuthenticationToken(loginDto.email(), loginDto.password());
			var auth = this.authenticationManager.authenticate(usernamePassword);
			var token = tokenService.genareteToken((Users) auth.getPrincipal());
			
			return ResponseEntity.ok(new LoginResponseDTO(token));
	}
	
	@Transactional
	public ResponseEntity<Object> registerCompany(@RequestBody RegisterDTO registerDto){
			if(this.companiesRepository.existsByDocument(registerDto.documentCompany())) {
				return ResponseEntity
						.status(HttpStatus.BAD_REQUEST)
						.body("Falha ao cadastrar empresa: " + "Erro ao tentar cadastrar empresa. Entre em contato com o administrador!");
			}
					
			if(this.userRepository.findByEmail(registerDto.emailUser()) != null) {
				return ResponseEntity
						.status(HttpStatus.BAD_REQUEST)
						.body("Falha ao cadastrar empresa: " + "Erro ao tentar cadastrar empresa. Entre em contato com o administrador!");
			}
	
			Companies newCompany = this.companiesRepository.save(new Companies().registerCompanyForUnauthenticatedUser(registerDto));
			
			String encryptedPassword = new BCryptPasswordEncoder().encode(registerDto.passwordUser());
			Users newUser = new Users(registerDto.emailUser(), encryptedPassword, registerDto.nameUser(), newCompany, UserTypeEnum.ADMIN);
			
			this.userRepository.save(newUser);
			
			return ResponseEntity.ok(String.format("Empresa '%s' cadastrada com sucesso", registerDto.nameCompany()));
	}
	

}
 