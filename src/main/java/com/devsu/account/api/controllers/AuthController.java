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
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.devsu.account.application.command.LoginCommand;
import com.devsu.account.application.dtos.responses.LoginResponse;

import io.jkratz.mediator.core.Mediator;

/**
 *
 * @author diego
 */
@RestController
@RequestMapping("/api/auth")
public class AuthController {

	private static final Logger logger = LoggerFactory.getLogger(AuthController.class);

	@Autowired
	private Mediator mediator;

	@PostMapping("/login")
	public ResponseEntity<LoginResponse> loginHandler(@Valid @RequestBody LoginCommand command) {
		logger.info("------ Sending command: {} ", command.getClass().getName());
		return new ResponseEntity<>(this.mediator.dispatch(command), HttpStatus.OK);
	}

}
