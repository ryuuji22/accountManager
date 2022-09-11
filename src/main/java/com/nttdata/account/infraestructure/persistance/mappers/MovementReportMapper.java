package com.nttdata.account.infraestructure.persistance.mappers;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.nttdata.account.application.dtos.responses.MovementReport;
import com.nttdata.account.infraestructure.models.MovementModel;

@Mapper(componentModel = "spring", uses = { AccountMapper.class })
public interface MovementReportMapper {

	@Mapping(source = "movementDate", target = "movementDate")
	@Mapping(source = "account.client.names", target = "clientName")
	@Mapping(source = "account.accountNumber", target = "accountNumber")
	@Mapping(source = "account.type", target = "accountType")
	@Mapping(source = "account.openingBalance", target = "openingBalance")
	@Mapping(source = "movementValue", target = "movementValue")
	@Mapping(source = "balance", target = "balance")
	MovementReport toMovementReport(MovementModel movementModel);

	List<MovementReport> toMovementReports(List<MovementModel> movements);

}
