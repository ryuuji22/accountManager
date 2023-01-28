package com.devsu.account.infraestructure.repositories.jpa;

import org.springframework.data.jpa.repository.JpaRepository;

import com.devsu.account.infraestructure.models.MovementModel;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Optional;
import org.springframework.data.jpa.repository.Query;

/**
 *
 * @author diego
 */
public interface IJpaMovementRepository extends JpaRepository<MovementModel, String> {

    @Query("SELECT SUM(m.movementValue) FROM MovementModel m WHERE LOWER(m.type) = LOWER(:type) "
            + "AND m.movementDate >= :dateBefore "
            + "AND m.movementDate <= :dateAfter "
            + "AND m.account.accountNumber=:accountNumber")
    public Optional<BigDecimal> findWithdrawalSumOfDay(String accountNumber, String type,
            LocalDate dateBefore, LocalDate dateAfter);

}
