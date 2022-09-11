package com.nttdata.account.domain.interfaces.repositories;

import java.time.LocalDate;
import java.util.List;

import com.nttdata.account.application.dtos.responses.MovementReport;
import com.nttdata.account.domain.entities.Movement;

public interface IMovementRepository {

	List<MovementReport> getByClientIdentificationAndStartDateAndEndDate(String identification, LocalDate dateBefore,
			LocalDate dateAfter);

	String create(Movement movement);

}
