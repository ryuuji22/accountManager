package com.devsu.account.application.command.delete;


import javax.validation.constraints.NotBlank;

import io.jkratz.mediator.core.Command;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
public class DeleteClientCommand implements Command {

	@NotBlank(message = "Identification field can't be empty.")
	private String identification;

}

