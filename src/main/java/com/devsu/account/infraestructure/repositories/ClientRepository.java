/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.devsu.account.infraestructure.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.devsu.account.domain.entities.Client;
import com.devsu.account.domain.interfaces.repositories.IClientRepository;
import com.devsu.account.infraestructure.persistance.mappers.ClientMapper;
import com.devsu.account.infraestructure.repositories.jpa.IJpaClientRepository;

/**
 *
 * @author diego
 */
@Repository
public class ClientRepository implements IClientRepository {

	@Autowired
	private IJpaClientRepository jpaClientRepository;

	@Autowired
	private ClientMapper mapper;

	@Override
	public Optional<List<Client>> getAll() {
		return jpaClientRepository.findByEnabled(Boolean.TRUE).map(mapper::toClients);
	}

	@Override
	public String create(Client client) {
		var clientModel = mapper.toClientModel(client);
		return jpaClientRepository.save(clientModel).getId();
	}

	@Override
	public void delete(Client client) {
		client.setEnabled(Boolean.FALSE);
		this.jpaClientRepository.save(mapper.toClientModel(client));
	}

	@Override
	public void update(Client client) {
		this.jpaClientRepository.save(mapper.toClientModel(client));
	}

	@Override
	public Optional<Client> getByIdentification(String identification) {
		return this.jpaClientRepository.findByIdentificationAndEnabled(identification, Boolean.TRUE)
				.map(mapper::toClient);
	}

	@Override
	public Optional<Client> getById(String id) {
		return this.jpaClientRepository.findByIdAndEnabled(id, Boolean.TRUE).map(mapper::toClient);
	}

}
