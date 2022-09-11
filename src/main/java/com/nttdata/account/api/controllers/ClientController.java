/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nttdata.account.api.controllers;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.nttdata.account.application.command.edit.EditClientCommand;
import com.nttdata.account.application.queries.ReadClientByIdentificationQuery;
import com.nttdata.account.domain.entities.Client;

import io.jkratz.mediator.core.Mediator;

/**
 *
 * @author diego
 */
@RestController
@RequestMapping("/api/user/client")
public class ClientController {

	private static final Logger logger = LoggerFactory.getLogger(ClientController.class);

	@Autowired
	private Mediator mediator;

	@GetMapping
	public ResponseEntity<Client> readClient() {
		var query = new ReadClientByIdentificationQuery();
		logger.info("----- Sending Query: {} ", query.getClass().getName());
		return new ResponseEntity<>(this.mediator.dispatch(query), HttpStatus.OK);
	}

	@PatchMapping
	public ResponseEntity<Void> editClient(@Valid @RequestBody EditClientCommand command) {

		logger.info("------ Sending command: {} ", command.getClass().getName());

		this.mediator.dispatch(command);
		return ResponseEntity.status(HttpStatus.OK).build();
	}

}
