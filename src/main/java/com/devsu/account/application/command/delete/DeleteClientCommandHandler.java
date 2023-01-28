package com.devsu.account.application.command.delete;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.devsu.account.domain.entities.Client;
import com.devsu.account.domain.exceptions.ApplicationDomainException;
import com.devsu.account.domain.interfaces.repositories.IAccountRepository;
import com.devsu.account.domain.interfaces.repositories.IClientRepository;
import com.devsu.account.domain.interfaces.repositories.IUserRepository;

import io.jkratz.mediator.core.CommandHandler;

@Component
public class DeleteClientCommandHandler implements CommandHandler<DeleteClientCommand> {
	@Autowired
	private IClientRepository clientRepository;
	@Autowired
	private IUserRepository userRepository;
	@Autowired
	private IAccountRepository accountRepository;

	@Override
	@Transactional
	public void handle(DeleteClientCommand deleteClientCommand) {

		Client clientDb = this.validateClient(deleteClientCommand.getIdentification());

		this.deleteAccounts(clientDb);
		this.clientRepository.delete(clientDb);
		this.userRepository.delete(clientDb.getUser());

	}

	private Client validateClient(String identification) {
		Optional<Client> clientFound = clientRepository.getByIdentification(identification);
		if (!clientFound.isPresent()) {
			throw new ApplicationDomainException("Client with identification " + identification + " doesn't exist");
		}
		return clientFound.get();

	}

	private void deleteAccounts(Client clientDb) {
		var accounts = accountRepository.findByClientIdentification(clientDb.getIdentification());
		if (!accounts.isEmpty()) {
			for (var deleteAcount : accounts.get()) {
				accountRepository.delete(deleteAcount);
			}
		}

	}
}
