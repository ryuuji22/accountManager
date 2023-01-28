package com.devsu.account.application.command.create;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;

import java.math.BigDecimal;
import java.text.ParseException;
import java.util.Optional;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.internal.verification.VerificationModeFactory;
import org.springframework.test.context.junit4.SpringRunner;

import com.devsu.account.application.queries.ReadClientByIdentificationQuery;
import com.devsu.account.domain.entities.Account;
import com.devsu.account.domain.entities.Client;
import com.devsu.account.domain.entities.Movement;
import com.devsu.account.domain.enums.AccountType;
import com.devsu.account.domain.exceptions.ApplicationDomainException;
import com.devsu.account.domain.interfaces.repositories.IAccountRepository;
import com.devsu.account.domain.interfaces.repositories.IMovementRepository;

import io.jkratz.mediator.core.Mediator;

@RunWith(SpringRunner.class)
public class CreateMovementCommandHandlerTest {

	@InjectMocks
	private CreateMovementCommandHandler commandHandler;

	@Mock
	private IAccountRepository accountRepository;
	@Mock
	private IMovementRepository movementRepository;
	@Mock
	private Mediator mediator;

	private Client client;

	@Before
	public void setUp() throws ParseException {
		client = new Client();
		client.setIdentification("1700000001");

		var account = new Account();
		account.setAccountNumber("123456");
		account.setClient(client);
		account.setActualBalance(new BigDecimal("100"));
		account.setOpeningBalance(new BigDecimal("0"));
		account.setType(AccountType.SAVINGS);

		Mockito.doReturn(client).when(mediator).dispatch(any(ReadClientByIdentificationQuery.class));
		Mockito.doReturn(Optional.of(account)).when(accountRepository)
				.findByClientIdentificationAndAccountNumber(anyString(), anyString());
		Mockito.doReturn("1").when(movementRepository).create(any(Movement.class));
		Mockito.doNothing().when(accountRepository).update(any(Account.class));

	}

	@Test
	public void validateNotNullMocks() {
		assertThat(accountRepository).isNotNull();
		assertThat(mediator).isNotNull();
		assertThat(commandHandler).isNotNull();
		assertThat(movementRepository).isNotNull();

	}

	@Test
	public void shoudCreateMovement_WITHDRAWAL_withEnoughFounds() {
		CreateMovementCommand command = new CreateMovementCommand();
		command.setAccountNumber("123456");
		command.setAmount(new BigDecimal("-20"));

		var response = commandHandler.handle(command);

		assertThat(response.getActualBalance()).isEqualTo(new BigDecimal("80"));
		this.verifyCreateMovementIsCalledOnce();
		this.verifyUpdateAccountIsCalledOnce();

	}

	@Test
	public void shoudCreateMovement_WITHDRAWAL_withNOTEnoughFounds() {
		CreateMovementCommand command = new CreateMovementCommand();
		command.setAccountNumber("123456");
		command.setAmount(new BigDecimal("-200"));

		ApplicationDomainException e = Assert.assertThrows(ApplicationDomainException.class,
				() -> commandHandler.handle(command));

		String actualMessage = e.getMessage();

		assertTrue(actualMessage.contains("Not enough founds for this operation"));
	}

	@Test
	public void shoudCreateMovement_NotAccountFound() {
		Mockito.doReturn(Optional.empty()).when(accountRepository)
				.findByClientIdentificationAndAccountNumber(anyString(), anyString());

		CreateMovementCommand command = new CreateMovementCommand();
		command.setAccountNumber("123456");
		command.setAmount(new BigDecimal("-200"));

		ApplicationDomainException e = Assert.assertThrows(ApplicationDomainException.class,
				() -> commandHandler.handle(command));

		String actualMessage = e.getMessage();

		assertTrue(actualMessage.contains("Client " + client.getIdentification() + " doesn't have an account number "
				+ command.getAccountNumber()));
	}

	@Test
	public void shoudCreateMovement_DEPOSIT() {
		CreateMovementCommand command = new CreateMovementCommand();
		command.setAccountNumber("123456");
		command.setAmount(new BigDecimal("20"));

		var response = commandHandler.handle(command);

		assertThat(response.getActualBalance()).isEqualTo(new BigDecimal("120"));
		this.verifyCreateMovementIsCalledOnce();
		this.verifyUpdateAccountIsCalledOnce();

	}

	private void verifyCreateMovementIsCalledOnce() {
		Mockito.verify(movementRepository, VerificationModeFactory.times(1)).create(any(Movement.class));
		Mockito.reset(movementRepository);
	}

	private void verifyUpdateAccountIsCalledOnce() {
		Mockito.verify(accountRepository, VerificationModeFactory.times(1)).update(any(Account.class));
		Mockito.reset(accountRepository);
	}

}
