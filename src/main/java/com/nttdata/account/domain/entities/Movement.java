package com.nttdata.account.domain.entities;

import java.math.BigDecimal;
import java.time.LocalDate;

import com.nttdata.account.domain.enums.MovementType;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Movement {
	
	private String id;

	private LocalDate movementDate;

	private MovementType type;

	private BigDecimal movementValue;

	private BigDecimal balance;

	private Account account;

}
