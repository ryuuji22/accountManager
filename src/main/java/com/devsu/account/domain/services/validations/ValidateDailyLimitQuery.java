package com.devsu.account.domain.services.validations;

import com.devsu.account.domain.entities.Account;
import io.jkratz.mediator.core.Command;
import java.math.BigDecimal;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
public class ValidateDailyLimitQuery implements Command {

    private Account account;
    private BigDecimal withdrawalValue;

}
