package com.nttdata.account.application.command.create;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.nttdata.account.domain.entities.Account;
import com.nttdata.account.domain.entities.Client;
import com.nttdata.account.domain.enums.AccountType;
import com.nttdata.account.domain.exceptions.ApplicationDomainException;
import com.nttdata.account.domain.interfaces.repositories.IAccountRepository;
import com.nttdata.account.domain.interfaces.repositories.IClientRepository;

import io.jkratz.mediator.core.CommandHandler;
import lombok.SneakyThrows;

@Component
public class CreateAccountCommandHandler implements CommandHandler<CreateAccountCommand> {

	@Autowired
	private IClientRepository clientRepository;
	@Autowired
	private IAccountRepository accountRepository;

	@Override
	@SneakyThrows
	@Transactional
	public void handle(CreateAccountCommand command) {

		var client = this.validateClient(command.getIdentification());
		var accountType = AccountType.valueOf(command.getAccountType().toUpperCase());
		this.validateAccount(command.getAccountNumber());

		var newAccount = new Account();
		newAccount.setAccountNumber(command.getAccountNumber());
		newAccount.setType(accountType);
		newAccount.setOpeningBalance(command.getOpeningBalance());
		newAccount.setActualBalance(command.getOpeningBalance());
		newAccount.setClient(client);

		accountRepository.create(newAccount);

	}

	private Client validateClient(String identification) {
		var clientFound = clientRepository.getByIdentification(identification);
		if (!clientFound.isPresent()) {
			throw new ApplicationDomainException("Client doesn't exist for identification " + identification);
		}
		return clientFound.get();

	}

	private void validateAccount(String accountNumber) {
		var accountFound = accountRepository.findByAccountNumber(accountNumber);
		if (accountFound.isPresent()) {
			throw new ApplicationDomainException("Client " + accountFound.get().getClient().getIdentification()
					+ " already has an account number " + accountNumber);
		}
	}

}
