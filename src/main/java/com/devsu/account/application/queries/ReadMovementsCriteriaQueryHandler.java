package com.devsu.account.application.queries;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.devsu.account.application.dtos.responses.MovementReport;
import com.devsu.account.domain.interfaces.repositories.IMovementRepository;

import io.jkratz.mediator.core.Mediator;
import io.jkratz.mediator.core.RequestHandler;

@Component
public class ReadMovementsCriteriaQueryHandler implements RequestHandler<ReadMovementsCriteriaQuery, List<MovementReport>> {

	@Autowired
	private IMovementRepository movementRepository;
	@Autowired
	private Mediator mediator;

	@Override
	public List<MovementReport> handle(ReadMovementsCriteriaQuery query) {
		var loggedClient = this.mediator.dispatch(new ReadClientByIdentificationQuery());
		
		return movementRepository.getByClientIdentificationAndStartDateAndEndDate(loggedClient.getIdentification(),
				query.getBeforeDate(), query.getAfterDate());
	}

}
