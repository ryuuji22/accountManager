package com.devsu.account.application.queries;

import com.devsu.account.domain.entities.Account;

import io.jkratz.mediator.core.Request;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
public class ReadAccountByNumberQuery implements Request<Account> {

	private String number;

}
