package com.nttdata.account.application.command.edit;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.nttdata.account.application.queries.ReadAccountByNumberQuery;
import com.nttdata.account.domain.enums.AccountType;
import com.nttdata.account.domain.interfaces.repositories.IAccountRepository;

import io.jkratz.mediator.core.CommandHandler;
import io.jkratz.mediator.core.Mediator;
import lombok.SneakyThrows;

@Component
public class EditAccountCommandHandler implements CommandHandler<EditAccountCommand> {

	@Autowired
	private IAccountRepository accountRepository;
	@Autowired
	private Mediator mediator;

	@Override
	@SneakyThrows
	@Transactional
	public void handle(EditAccountCommand command) {

		var accountQuery = new ReadAccountByNumberQuery(command.getAccountNumber());
		var foundAccount = this.mediator.dispatch(accountQuery);
		
		var accountType = AccountType.valueOf(command.getAccountType().toUpperCase());

		foundAccount.setType(accountType);

		accountRepository.update(foundAccount);

	}

}
