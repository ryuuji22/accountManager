package com.nttdata.account.domain.entities;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Client {

	private String id;

	private String identification;

	private String names;

	private String email;

	private String address;

	private String phone;

	private Integer age;

	@JsonIgnore
	private User user;

	@JsonIgnore
	private Date createdAt;

	@JsonIgnore
	private Boolean enabled;

}
