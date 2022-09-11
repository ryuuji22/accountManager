package com.nttdata.account.api.controllers;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.http.ResponseEntity;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.nttdata.account.application.command.create.CreateMovementCommand;
import com.nttdata.account.application.dtos.responses.MovementReport;
import com.nttdata.account.application.queries.ReadMovementsCriteriaQuery;
import com.nttdata.account.domain.entities.Account;

import io.jkratz.mediator.core.Mediator;

/**
 *
 * @author diegohinojosa
 */
@RunWith(SpringRunner.class)
public class MovementControllerTest {

	@InjectMocks
	MovementController controller;

	@Mock
	Mediator mediator;

	@Test
	public void createMovement() {
		var command = new CreateMovementCommand();
		command.setAccountNumber("123456");
		command.setAmount(new BigDecimal("100"));

		var response = new Account();
		response.setId("1");
		response.setActualBalance(new BigDecimal("100"));
		response.setEnabled(Boolean.TRUE);

		MockHttpServletRequest request = new MockHttpServletRequest();
		RequestContextHolder.setRequestAttributes(new ServletRequestAttributes(request));

		Mockito.doReturn(response).when(mediator).dispatch(any(CreateMovementCommand.class));

		ResponseEntity<Account> responseEntity = controller.createMovement(command);

		assertThat(responseEntity.getStatusCodeValue()).isEqualTo(201);
		assertThat(responseEntity.getBody().getId()).isEqualTo("1");
	}

	@Test
	public void readMovementsByCriteria() {

		var movement1 = new MovementReport();
		movement1.setAccountNumber("123456");
		movement1.setMovementDate(LocalDate.of(2022, 9, 2));

		var movement2 = new MovementReport();
		movement2.setAccountNumber("789123");
		movement1.setMovementDate(LocalDate.of(2022, 9, 4));

		var movementReport = new ArrayList<MovementReport>();
		movementReport.add(movement1);
		movementReport.add(movement2);

		MockHttpServletRequest request = new MockHttpServletRequest();
		RequestContextHolder.setRequestAttributes(new ServletRequestAttributes(request));

		Mockito.doReturn(movementReport).when(mediator).dispatch(any(ReadMovementsCriteriaQuery.class));

		LocalDate beforeDate = LocalDate.of(2022, 9, 1);
		LocalDate afterDate = LocalDate.of(2022, 9, 10);

		ResponseEntity<List<MovementReport>> responseEntity = controller.readMovementsByCriteria(beforeDate, afterDate);

		assertThat(responseEntity.getStatusCodeValue()).isEqualTo(200);
		assertThat(responseEntity.getBody()).isNotEmpty();
	}

}
