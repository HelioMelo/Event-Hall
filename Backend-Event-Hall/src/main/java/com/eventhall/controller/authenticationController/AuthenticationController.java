package com.eventhall.controller.authenticationController;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.eventhall.dto.request.LoginDTO;
import com.eventhall.dto.request.RegisterDTO;
import com.eventhall.service.auth.AuthorizationService;

import io.swagger.v3.oas.annotations.parameters.RequestBody;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/auth")
public class AuthenticationController {

	@Autowired
	private AuthorizationService authorizationservice;

	@PostMapping("/login")
	public ResponseEntity<Object> login(@RequestBody @Valid LoginDTO loginDTO) {
		return authorizationservice.login(loginDTO);
	}

	
	
	@PostMapping("/register/company")
	public ResponseEntity<Object> register(@RequestBody @Valid RegisterDTO registerDTO) {
		return authorizationservice.registerCompany(registerDTO);
	}

}