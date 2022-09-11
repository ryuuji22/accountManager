package com.nttdata.account.application.command;

import javax.validation.constraints.NotBlank;

import com.nttdata.account.application.dtos.responses.LoginResponse;

import io.jkratz.mediator.core.Request;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class LoginCommand implements Request<LoginResponse> {

	@NotBlank(message = "Identification field can't be empty.")
	private String identification;

	@NotBlank(message = "Password field can't be empty.")
	private String password;

}
