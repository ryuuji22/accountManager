package com.devsu.account.application.command.create;

import java.math.BigDecimal;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.devsu.account.application.queries.ReadClientByIdentificationQuery;
import com.devsu.account.domain.services.validations.ValidateDailyLimitQuery;
import com.devsu.account.domain.entities.Account;
import com.devsu.account.domain.entities.Movement;
import com.devsu.account.domain.enums.MovementType;
import com.devsu.account.domain.exceptions.ApplicationDomainException;
import com.devsu.account.domain.interfaces.repositories.IAccountRepository;
import com.devsu.account.domain.interfaces.repositories.IMovementRepository;

import io.jkratz.mediator.core.Mediator;
import io.jkratz.mediator.core.RequestHandler;
import lombok.SneakyThrows;

@Component
public class CreateMovementCommandHandler implements RequestHandler<CreateMovementCommand, Account> {

    @Autowired
    private IAccountRepository accountRepository;
    @Autowired
    private IMovementRepository movementRepository;
    @Autowired
    private Mediator mediator;

    @Override
    @SneakyThrows
    @Transactional
    public Account handle(CreateMovementCommand command) {

        var loggedClient = this.mediator.dispatch(new ReadClientByIdentificationQuery());
        var accountFound = this.validateAccount(loggedClient.getIdentification(), command.getAccountNumber());

        return this.doOperation(accountFound, command.getAmount());

    }

    private Account doOperation(Account account, BigDecimal amount) {
        var movementType = amount.compareTo(new BigDecimal("0")) >= 0 ? MovementType.DEPOSIT : MovementType.WITHDRAWAL;
        this.checkFounds(account, amount, movementType);
        this.validateWithdrawalDailyLimit(account, amount);
        var newMovement = new Movement();
        newMovement.setAccount(account);
        newMovement.setMovementValue(amount);
        newMovement.setType(movementType);
        var balance = account.getActualBalance().add(amount);
        newMovement.setBalance(balance);
        movementRepository.create(newMovement);
        account.setActualBalance(balance);
        accountRepository.update(account);
        return account;
    }

    private Account validateAccount(String identification, String accountNumber) {
        var accountFound = accountRepository.findByClientIdentificationAndAccountNumber(identification, accountNumber);
        if (!accountFound.isPresent()) {
            throw new ApplicationDomainException(
                    "Client " + identification + " doesn't have an account number " + accountNumber);
        }
        return accountFound.get();
    }

    private void checkFounds(Account account, BigDecimal amount, MovementType type) {

        if (type.equals(MovementType.WITHDRAWAL)
                && account.getActualBalance().compareTo(amount.multiply(new BigDecimal("-1"))) < 0) {

            throw new ApplicationDomainException("Not enough founds for this operation");
        }

    }

    private void validateWithdrawalDailyLimit(Account account, BigDecimal amount) {
        var limitQuery = new ValidateDailyLimitQuery();
        limitQuery.setAccount(account);
        limitQuery.setWithdrawalValue(amount);
        mediator.dispatch(limitQuery);
    }

}
