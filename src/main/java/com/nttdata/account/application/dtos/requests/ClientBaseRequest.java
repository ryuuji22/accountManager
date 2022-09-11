package com.nttdata.account.application.dtos.requests;

import javax.validation.constraints.Digits;
import javax.validation.constraints.Email;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class ClientBaseRequest {
	
	@NotBlank(message = "Identification field can't be empty.")
	private String identification;

	@NotBlank(message = "Names field can't be empty.")
	private String names;
	
	@Min(value = 18,message = "Min age value must be 18")
	private Integer age;

	@Pattern(regexp = "^[a-zA-Z0-9.\\-\\/+=@_ ]*$")
	@Email(message = "You must provide a valid email format")
	private String email;
	
	private String address;

	@NotBlank(message = "Phone number field can't be empty.")
	@Digits(message = "Phone Number Only digits allowed", fraction = 0, integer = 12)
	private String phone;
	
	

}
