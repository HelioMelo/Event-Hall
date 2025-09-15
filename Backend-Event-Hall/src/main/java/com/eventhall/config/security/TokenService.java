package com.eventhall.config.security;


import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.eventhall.entities.Users;

@Service
public class TokenService {

	@Value("${api.security.token.secret}")
	private String secret;
	
	public String genareteToken(Users user) {
		try {
			Algorithm algorithm = Algorithm.HMAC256(secret);
			
			String token = JWT.create()				
					.withIssuer("auth") // quem é o emissor
					.withSubject(user.getUsername()) // usuário que recebe o token
					.withExpiresAt(genExpirationDate())
					.sign(algorithm); // faz a assinatura e geração final

			return token;

		} catch (JWTCreationException e) {
			throw new RuntimeException("Erro while generating token" + e);
		}
	}

	public String validateToken(String token) {
		try {

			Algorithm algorithm = Algorithm.HMAC256(secret);
			
			return JWT.require(algorithm)
					.withIssuer("auth")
					.build()
					.verify(token) // descriptogrando o token
					.getSubject(); // pegando o token que salvamos dentro do getSubject

		} catch (JWTCreationException e) {
			return "";
		}

	}

	public Instant genExpirationDate() {
		return LocalDateTime.now().plusHours(12).toInstant(ZoneOffset.of("-03:00"));
	}

}