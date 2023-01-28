package com.devsu.account.application.dtos.responses;

import java.math.BigDecimal;
import java.time.LocalDate;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MovementReport {

	private LocalDate movementDate;
	private String clientName;
	private String accountNumber;
	private String accountType;
	private BigDecimal openingBalance;
	private BigDecimal movementValue;
	private BigDecimal balance;

}
