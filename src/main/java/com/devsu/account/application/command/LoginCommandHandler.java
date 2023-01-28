package com.devsu.account.application.command;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Component;

import com.devsu.account.application.dtos.responses.LoginResponse;
import com.devsu.account.domain.exceptions.ApplicationDomainException;
import com.devsu.account.infraestructure.security.JWTUtil;

import io.jkratz.mediator.core.RequestHandler;

@Component
public class LoginCommandHandler implements RequestHandler<LoginCommand, LoginResponse> {

	@Autowired
	private JWTUtil jwtUtil;
	@Autowired
	private AuthenticationManager authManager;

	@Override
	@Transactional
	public LoginResponse handle(LoginCommand command) {
		try {
			var authInputToken = new UsernamePasswordAuthenticationToken(
					command.getIdentification(), command.getPassword());

			authManager.authenticate(authInputToken);

			String token = jwtUtil.generateToken(command.getIdentification());

			return new LoginResponse(token);
		} catch (AuthenticationException authExc) {

			throw new ApplicationDomainException("Invalid Login Credentials");

		}
	}

}
