package com.nttdata.account.application.command.edit;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.nttdata.account.application.queries.ReadClientByIdentificationQuery;
import com.nttdata.account.domain.exceptions.ApplicationDomainException;
import com.nttdata.account.domain.interfaces.repositories.IClientRepository;
import com.nttdata.account.domain.interfaces.repositories.IUserRepository;
import com.nttdata.account.domain.services.create.CreateUserService;
import com.nttdata.account.domain.services.validations.ValidateIdentificationCIService;

import io.jkratz.mediator.core.CommandHandler;
import io.jkratz.mediator.core.Mediator;
import lombok.SneakyThrows;

@Component
public class EditClientCommandHandler implements CommandHandler<EditClientCommand> {

	@Autowired
	private IClientRepository clientRepository;
	@Autowired
	private IUserRepository userRepository;
	@Autowired
	private Mediator mediator;

	@Override
	@Transactional
	@SneakyThrows
	public void handle(EditClientCommand command) {

		var loggedClient = this.mediator.dispatch(new ReadClientByIdentificationQuery());
		
		var isCIValid = this.mediator.dispatch(new ValidateIdentificationCIService(command.getIdentification()));

		if (Boolean.FALSE.equals(isCIValid)) {
			throw new ApplicationDomainException("Identification " + command.getIdentification() + " is not valid");
		}
		
		var createUserService = new CreateUserService();
		createUserService.setIdentification(command.getIdentification());
		createUserService.setPassword(command.getPassword());
		var user = this.mediator.dispatch(createUserService);
		
		user.setRoles(loggedClient.getUser().getRoles());
		user.setId(loggedClient.getUser().getId());
		user.setEnabled(loggedClient.getUser().getEnabled());
		user.setCreatedAt(loggedClient.getUser().getCreatedAt());

		loggedClient.setIdentification(command.getIdentification());
		loggedClient.setNames(command.getNames());
		loggedClient.setAge(command.getAge());
		loggedClient.setEmail(command.getEmail());
		loggedClient.setAddress(command.getAddress());
		loggedClient.setPhone(command.getPhone());
		loggedClient.setUser(user);

		clientRepository.update(loggedClient);
		userRepository.update(user);

	}

}
