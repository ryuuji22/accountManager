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
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.nttdata.account.application.command.create.CreateAccountCommand;
import com.nttdata.account.application.command.delete.DeleteAccountCommand;
import com.nttdata.account.application.command.edit.EditAccountCommand;
import com.nttdata.account.application.dtos.requests.EditAccountRequest;

import io.jkratz.mediator.core.Mediator;

/**
 *
 * @author diego
 */
@RestController
@RequestMapping("/api/admin/account")
public class AccountController {

	private static final Logger logger = LoggerFactory.getLogger(AccountController.class);

	@Autowired
	private Mediator mediator;

	@DeleteMapping(path = "/{accountNumber}")
	public ResponseEntity<Void> deleteAccount(@PathVariable("accountNumber") String accountNumber) {
		var deleteCommand = new DeleteAccountCommand();
		deleteCommand.setAccountNumber(accountNumber);

		logger.info("--------- Sending command: {} ", deleteCommand.getClass().getName());
		this.mediator.dispatch(deleteCommand);
		return ResponseEntity.status(HttpStatus.OK).build();
	}

	@PostMapping
	public ResponseEntity<Void> createAccount(@Valid @RequestBody CreateAccountCommand command) {

		logger.info("------ Sending command: {} ", command.getClass().getName());

		this.mediator.dispatch(command);
		return ResponseEntity.status(HttpStatus.CREATED).build();
	}

	@PatchMapping(path = "/{accountNumber}")
	public ResponseEntity<Void> editAccount(@PathVariable("accountNumber") String accountNumber,
			@Valid @RequestBody EditAccountRequest request) {

		var editCommand = new EditAccountCommand();
		editCommand.setAccountNumber(accountNumber);
		editCommand.setAccountType(request.getAccountType());

		logger.info("------ Sending command: {} ", editCommand.getClass().getName());

		this.mediator.dispatch(editCommand);
		return ResponseEntity.status(HttpStatus.OK).build();
	}

}
