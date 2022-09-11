package com.nttdata.account.application.command.delete;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.nttdata.account.application.queries.ReadAccountByNumberQuery;
import com.nttdata.account.domain.interfaces.repositories.IAccountRepository;

import io.jkratz.mediator.core.CommandHandler;
import io.jkratz.mediator.core.Mediator;

@Component
public class DeleteAccountCommandHandler implements CommandHandler<DeleteAccountCommand> {
	@Autowired
	private IAccountRepository accountRepository;
	@Autowired
	private Mediator mediator;

	@Override
	@Transactional
	public void handle(DeleteAccountCommand deleteAccountCommand) {

		var accountQuery = new ReadAccountByNumberQuery(deleteAccountCommand.getAccountNumber());
		var foundAccount = this.mediator.dispatch(accountQuery);

		this.accountRepository.delete(foundAccount);

	}

}
