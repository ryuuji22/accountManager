/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nttdata.account.api.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.nttdata.account.application.queries.ReadUserDetailsQuery;
import com.nttdata.account.domain.entities.User;

import io.jkratz.mediator.core.Mediator;

/**
 *
 * @author diego
 */
@RestController
@RequestMapping("/api/user")
public class UserController {

	private static final Logger logger = LoggerFactory.getLogger(UserController.class);

	@Autowired
	private Mediator mediator;

	@GetMapping("/info")
	public ResponseEntity<User> getUserDetails() {

		ReadUserDetailsQuery query = new ReadUserDetailsQuery();
		logger.info("------ Sending command: {} ", query.getClass().getName());
		return new ResponseEntity<>(this.mediator.dispatch(query), HttpStatus.OK);
	}

}
