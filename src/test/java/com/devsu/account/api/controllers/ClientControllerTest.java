package com.devsu.account.api.controllers;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;

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

import com.devsu.account.application.command.edit.EditClientCommand;
import com.devsu.account.application.queries.ReadClientByIdentificationQuery;
import com.devsu.account.domain.entities.Client;

import io.jkratz.mediator.core.Mediator;

/**
 *
 * @author diegohinojosa
 */
@RunWith(SpringRunner.class)
public class ClientControllerTest {

	@InjectMocks
	ClientController controller;

	@Mock
	Mediator mediator;

	@Test
	public void readClient() {
		var response = new Client();
		response.setId("1");
		response.setIdentification("1700000001");
		response.setNames("Test Test");
		response.setEnabled(Boolean.TRUE);

		MockHttpServletRequest request = new MockHttpServletRequest();
		RequestContextHolder.setRequestAttributes(new ServletRequestAttributes(request));

		Mockito.doReturn(response).when(mediator).dispatch(any(ReadClientByIdentificationQuery.class));

		ResponseEntity<Client> responseEntity = controller.readClient();

		assertThat(responseEntity.getStatusCodeValue()).isEqualTo(200);
		assertThat(responseEntity.getBody().getId()).isEqualTo("1");
	}

	@Test
	public void editClient() {
		var command = new EditClientCommand();
		command.setIdentification("1700000001");
		command.setNames("Test Test");
		command.setEmail("email@test.com");

		MockHttpServletRequest request = new MockHttpServletRequest();
		RequestContextHolder.setRequestAttributes(new ServletRequestAttributes(request));

		Mockito.doNothing().when(mediator).dispatch(any(EditClientCommand.class));

		ResponseEntity<Void> responseEntity = controller.editClient(command);

		assertThat(responseEntity.getStatusCodeValue()).isEqualTo(200);
	}

}
