/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.devsu.account.domain.entities;

import java.math.BigDecimal;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.devsu.account.domain.enums.AccountType;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Account {

	private String id;

	private String accountNumber;

	private AccountType type;

	@JsonIgnore
	private BigDecimal openingBalance;

	private BigDecimal actualBalance;
        
        @JsonIgnore
	private BigDecimal dailyWithdrawalLimit;

	@JsonIgnore
	private Client client;

	@JsonIgnore
	private Date createdAt;

	@JsonIgnore
	private Boolean enabled;

}
