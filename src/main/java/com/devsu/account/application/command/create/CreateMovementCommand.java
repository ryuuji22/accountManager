package com.devsu.account.application.command.create;

import java.math.BigDecimal;

import javax.validation.constraints.Digits;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import com.devsu.account.domain.entities.Account;

import io.jkratz.mediator.core.Request;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class CreateMovementCommand implements Request<Account> {

	@NotBlank(message = "Account Number field can't be empty.")
	@Digits(message = "Account Number Only digits allowed", fraction = 0, integer = 12)
	private String accountNumber;

	@NotNull(message = "Amount field can't be null.")
	private BigDecimal amount;
}
