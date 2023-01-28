package com.devsu.account.application.queries;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.devsu.account.domain.entities.Account;
import com.devsu.account.domain.exceptions.ApplicationDomainException;
import com.devsu.account.domain.interfaces.repositories.IAccountRepository;

import io.jkratz.mediator.core.RequestHandler;

@Component
public class ReadAccountByNumberQueryHandler implements RequestHandler<ReadAccountByNumberQuery, Account> {

	@Autowired
	private IAccountRepository accountRepository;

	@Override
	public Account handle(ReadAccountByNumberQuery query) {
		var accountFound = accountRepository.findByAccountNumber(query.getNumber());
		if (!accountFound.isPresent()) {
			throw new ApplicationDomainException("Account number " + query.getNumber() + " doesn't exist.");
		}
		return accountFound.get();
	}

}
