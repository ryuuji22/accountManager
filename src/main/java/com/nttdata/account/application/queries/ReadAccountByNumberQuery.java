package com.nttdata.account.application.queries;

import com.nttdata.account.domain.entities.Account;

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
