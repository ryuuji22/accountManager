/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nttdata.account.infraestructure.repositories.jpa;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.nttdata.account.infraestructure.models.ClientModel;

/**
 *
 * @author diego
 */
public interface IJpaClientRepository extends JpaRepository<ClientModel, String> {
	public Optional<ClientModel> findByIdentificationAndEnabled(String identification, Boolean enabled);

	public Optional<List<ClientModel>> findByEnabled(Boolean enabled);

	public Optional<ClientModel> findByIdAndEnabled(String id, Boolean enabled);

}
