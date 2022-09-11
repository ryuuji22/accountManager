package com.nttdata.account.domain.interfaces.repositories;

import java.util.List;
import java.util.Optional;

import com.nttdata.account.domain.entities.Client;

public interface IClientRepository {

	Optional<List<Client>> getAll();

	Optional<Client> getByIdentification(String identification);
	
	Optional<Client> getById(String id);

	String create(Client client);

	void delete(Client client);

	void update(Client client);

}
