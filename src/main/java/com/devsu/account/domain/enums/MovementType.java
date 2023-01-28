package com.devsu.account.domain.enums;

public enum MovementType {
	DEPOSIT("deposit"), WITHDRAWAL("withdrawal");

	String movement;

	MovementType(String movement) {
		this.movement = movement;
	}

}
