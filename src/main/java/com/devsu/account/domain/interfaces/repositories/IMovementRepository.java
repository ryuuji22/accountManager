package com.devsu.account.domain.interfaces.repositories;

import java.time.LocalDate;
import java.util.List;

import com.devsu.account.application.dtos.responses.MovementReport;
import com.devsu.account.domain.entities.Movement;
import java.math.BigDecimal;
import java.util.Optional;

public interface IMovementRepository {

    List<MovementReport> getByClientIdentificationAndStartDateAndEndDate(String identification, LocalDate dateBefore,
            LocalDate dateAfter);

    String create(Movement movement);

    Optional<BigDecimal> findWithdrawalSumOfDay(String accountNumber, LocalDate dateBefore, LocalDate dateAfter);

}
