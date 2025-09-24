package com.eventhall.config.security;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

//classe para deabilitar autenticação do spring security e deixar  para nós criar as regras
@Configuration
@EnableWebSecurity //habilitar as configuração web security
public class SecurityConfiguration {
		
	@Autowired
	SecurityFilter securityFilter;
	
	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
	    return httpSecurity
	        .csrf(csrf -> csrf.disable())
	        .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
	        .authorizeHttpRequests(authorize -> authorize
	            .requestMatchers(HttpMethod.POST, "/api/clients").hasRole("ADMIN")
	            .requestMatchers(HttpMethod.GET, "/api/clients").hasRole("ADMIN")
	            .requestMatchers(HttpMethod.POST, "/api/auth/login").permitAll()
	            .requestMatchers(HttpMethod.POST, "/api/auth/register/company").permitAll()
	            .requestMatchers("/v3/api-docs/**", "/swagger-ui/**", "/swagger-ui.html").permitAll()
	            .anyRequest().authenticated()
	        )
	        // O addFilterBefore deve ser chamado aqui, antes do build()
	        .addFilterBefore(securityFilter, UsernamePasswordAuthenticationFilter.class)
	        .build();
	}	
	
	
	@Bean
	public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {	
		return authenticationConfiguration.getAuthenticationManager(); //pegando a instancia do autheticationManage do spring security	
	}
	
	@Bean
	//criptografando a senha, toda vez que criar ele vai criptografar a senha no banco e quando logar ele vai comparar se a senha é igual
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}	
}