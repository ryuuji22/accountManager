package com.nttdata.account.application.command.delete;


import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

import io.jkratz.mediator.core.Command;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
public class DeleteAccountCommand implements Command {

	@NotBlank(message = "Account Number field can't be empty.")
	@Pattern(message = "Account Number Only digits allowed", regexp = "[\\d]")
	private String accountNumber;

}

