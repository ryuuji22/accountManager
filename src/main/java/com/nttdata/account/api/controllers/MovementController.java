/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nttdata.account.api.controllers;

import java.time.LocalDate;
import java.util.List;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.nttdata.account.application.command.create.CreateMovementCommand;
import com.nttdata.account.application.dtos.responses.MovementReport;
import com.nttdata.account.application.queries.ReadMovementsCriteriaQuery;
import com.nttdata.account.domain.entities.Account;

import io.jkratz.mediator.core.Mediator;

/**
 *
 * @author diego
 */
@RestController
@RequestMapping("/api/user/movement")
public class MovementController {

	private static final Logger logger = LoggerFactory.getLogger(MovementController.class);

	@Autowired
	private Mediator mediator;

	@PostMapping
	public ResponseEntity<Account> createMovement(@Valid @RequestBody CreateMovementCommand command) {

		logger.info("------ Sending command: {} ", command.getClass().getName());

		return new ResponseEntity<>(this.mediator.dispatch(command), HttpStatus.CREATED);
	}

	@GetMapping("/report")
	public ResponseEntity<List<MovementReport>> readMovementsByCriteria(
			@RequestParam("beforeDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate beforeDate,
			@RequestParam("afterDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate afterDate) {
		var criteriaQuery = new ReadMovementsCriteriaQuery();
		criteriaQuery.setBeforeDate(beforeDate);
		criteriaQuery.setAfterDate(afterDate);

		logger.info("------ Sending command: {} ", criteriaQuery.getClass().getName());

		return new ResponseEntity<>(this.mediator.dispatch(criteriaQuery), HttpStatus.OK);
	}

}
