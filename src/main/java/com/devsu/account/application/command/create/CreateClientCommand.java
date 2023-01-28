package com.devsu.account.application.command.create;

import javax.validation.constraints.NotBlank;

import com.devsu.account.application.dtos.requests.ClientBaseRequest;

import io.jkratz.mediator.core.Command;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class CreateClientCommand extends ClientBaseRequest implements Command {

	@NotBlank(message = "Password field can't be empty.")
	private String password;
}
