package com.nttdata.account.application.command.create;

import java.math.BigDecimal;

import javax.validation.constraints.Digits;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;

import io.jkratz.mediator.core.Command;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class CreateAccountCommand implements Command {

	@NotBlank(message = "Identification field can't be empty.")
	private String identification;

	@NotBlank(message = "Account Number field can't be empty.")
	@Digits(message = "Account Number Only digits allowed", fraction = 0, integer = 12)
	private String accountNumber;

	@NotBlank(message = "Account Type field can't be empty.")
	private String accountType;

	@Min(value = 0, message = "Opening Balance value must be positive")
	private BigDecimal openingBalance;
}
