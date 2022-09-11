package com.nttdata.account.application.command.create;

import java.util.ArrayList;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.nttdata.account.domain.entities.Client;
import com.nttdata.account.domain.exceptions.ApplicationDomainException;
import com.nttdata.account.domain.interfaces.repositories.IClientRepository;
import com.nttdata.account.domain.services.create.CreateUserService;
import com.nttdata.account.domain.services.validations.ValidateIdentificationCIService;

import io.jkratz.mediator.core.CommandHandler;
import io.jkratz.mediator.core.Mediator;

@Component
public class CreateClientCommandHandler implements CommandHandler<CreateClientCommand> {

	@Autowired
	private IClientRepository clientRepository;
	@Autowired
	private Mediator mediator;
	
	private static final String DEFAULT_CLIENT_ROLE="ROLE_USER";

	@Override
	@Transactional
	public void handle(CreateClientCommand command) {

		var isCIValid = this.mediator.dispatch(new ValidateIdentificationCIService(command.getIdentification()));

		if (Boolean.FALSE.equals(isCIValid)) {
			throw new ApplicationDomainException("Identification " + command.getIdentification() + " is not valid");
		}

		this.validateClient(command.getIdentification());
		
		var roles=new ArrayList<String>();
		roles.add(DEFAULT_CLIENT_ROLE);

		var createUserService = new CreateUserService();
		createUserService.setIdentification(command.getIdentification());
		createUserService.setRoles(roles);
		createUserService.setPassword(command.getPassword());
		var user = this.mediator.dispatch(createUserService);

		var newClient = new Client();
		newClient.setIdentification(command.getIdentification());
		newClient.setNames(command.getNames());
		newClient.setAge(command.getAge());
		newClient.setEmail(command.getEmail());
		newClient.setAddress(command.getAddress());
		newClient.setPhone(command.getPhone());
		newClient.setUser(user);

		clientRepository.create(newClient);

	}

	private void validateClient(String identification) {
		var clientFound = clientRepository.getByIdentification(identification);
		if (clientFound.isPresent()) {
			throw new ApplicationDomainException("Client already exists for identification " + identification);
		}

	}
	
	
}
