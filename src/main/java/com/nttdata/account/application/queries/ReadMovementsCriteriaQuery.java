package com.nttdata.account.application.queries;

import java.time.LocalDate;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.nttdata.account.application.dtos.responses.MovementReport;

import io.jkratz.mediator.core.Request;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
public class ReadMovementsCriteriaQuery implements Request<List<MovementReport>> {
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd", locale = "es_EC")
	private LocalDate beforeDate;
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd", locale = "es_EC")
	private LocalDate afterDate;

}
