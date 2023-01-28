package com.devsu.account.application.queries;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import com.devsu.account.domain.entities.Client;
import com.devsu.account.domain.exceptions.ApplicationDomainException;
import com.devsu.account.domain.interfaces.repositories.IClientRepository;

import io.jkratz.mediator.core.RequestHandler;

@Component
public class ReadClientByIdentificationQueryHandler
		implements RequestHandler<ReadClientByIdentificationQuery, Client> {

	@Autowired
	private IClientRepository clientRepository;

	@Override
	public Client handle(ReadClientByIdentificationQuery query) {
		String identification = (String) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		return clientRepository.getByIdentification(identification)
				.orElseThrow(() -> new ApplicationDomainException(
				"Client with identification " + identification + " doesn't exist."));
	}

}
