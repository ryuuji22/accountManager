package com.devsu.account.domain.services.validations;

import com.devsu.account.domain.exceptions.ApplicationDomainException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.devsu.account.domain.interfaces.repositories.IMovementRepository;
import io.jkratz.mediator.core.CommandHandler;
import java.math.BigDecimal;

import java.time.LocalDate;

@Component
public class ValidateDailyLimitQueryHandler implements CommandHandler<ValidateDailyLimitQuery> {

    @Autowired
    private IMovementRepository movementRepository;

    @Override
    public void handle(ValidateDailyLimitQuery query) {
        if (query.getWithdrawalValue().compareTo(new BigDecimal(0)) < 0) {

            var beforeDate = LocalDate.now();
            var afterDate = beforeDate.plusDays(1);
            var currentSumOpt = movementRepository.findWithdrawalSumOfDay(query.getAccount().getAccountNumber(),
                    beforeDate, afterDate);
            BigDecimal currentSum = new BigDecimal(0);
            if (currentSumOpt.isPresent()) {
                currentSum = currentSumOpt.get();
            }

            var total = currentSum.add(query.getWithdrawalValue());
            var totalAbs = total.abs();
            if (totalAbs.compareTo(query.getAccount().getDailyWithdrawalLimit()) > 0) {
                throw new ApplicationDomainException("Withdrawal daily limit of "
                        + query.getAccount().getDailyWithdrawalLimit() + " will be exceeded");
            }
        }
    }

}
