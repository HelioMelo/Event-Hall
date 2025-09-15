package com.eventhall.config.security;



import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.eventhall.repository.UserRepository;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class SecurityFilter extends OncePerRequestFilter { // OncePerRequestFilter acontece cada vez a uma requisição

	@Autowired
	TokenService tokenService;

	@Autowired
	UserRepository userRepository;

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

		var token = this.recoverToken(request);

		if (token != null) {
			var email = tokenService.validateToken(token);
			UserDetails user = userRepository.findByEmail(email); // encotrando o usuário
			
			if (user != null) {
				var authentication = new UsernamePasswordAuthenticationToken(user, null, user.getAuthorities());
				SecurityContextHolder.getContext().setAuthentication(authentication);// salvamos o contexto do usuário
			}
		}
		filterChain.doFilter(request, response); // passando para o proximo filtro
	}

	private String recoverToken(HttpServletRequest request) {
		var authHeader = request.getHeader("Authorization");
		if (authHeader == null) return null;
		return authHeader.replace("Bearer ", ""); // substituido o valor do bearer para vazio para pegar só´o valor do												// token
	}
}