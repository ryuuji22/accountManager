package com.devsu.account.application.command.edit;

import com.devsu.account.application.dtos.requests.ClientBaseRequest;

import io.jkratz.mediator.core.Command;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class EditClientCommand extends ClientBaseRequest implements Command {
	
	private String password;
}
