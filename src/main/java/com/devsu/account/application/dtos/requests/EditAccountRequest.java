package com.devsu.account.application.dtos.requests;

import javax.validation.constraints.NotBlank;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class EditAccountRequest {

	@NotBlank(message = "Account Type field can't be empty.")
	private String accountType;

}
