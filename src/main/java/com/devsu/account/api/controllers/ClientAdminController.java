/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.devsu.account.api.controllers;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.devsu.account.application.command.create.CreateClientCommand;
import com.devsu.account.application.command.delete.DeleteClientCommand;

import io.jkratz.mediator.core.Mediator;

/**
 *
 * @author diego
 */
@RestController
@RequestMapping("/api/admin/client")
public class ClientAdminController {

	private static final Logger logger = LoggerFactory.getLogger(ClientAdminController.class);

	@Autowired
	private Mediator mediator;

	@DeleteMapping(path = "/{identification}")
	public ResponseEntity<Void> deleteClient(@PathVariable("identification") String identification) {
		var deleteCommand = new DeleteClientCommand();
		deleteCommand.setIdentification(identification);
		logger.info("--------- Sending command: {} ", deleteCommand.getClass().getName());
		this.mediator.dispatch(deleteCommand);
		return ResponseEntity.status(HttpStatus.OK).build();
	}

	@PostMapping
	public ResponseEntity<Void> createClient(@Valid @RequestBody CreateClientCommand command) {

		logger.info("------ Sending command: {} ", command.getClass().getName());

		this.mediator.dispatch(command);
		return ResponseEntity.status(HttpStatus.CREATED).build();
	}

}
