package com.devsu.account.infraestructure.persistance.mappers;

import java.util.List;

import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.devsu.account.domain.entities.Movement;
import com.devsu.account.infraestructure.models.MovementModel;

@Mapper(componentModel = "spring")
public interface MovementMapper {

	@Mapping(source = "id", target = "id")
	@Mapping(source = "movementDate", target = "movementDate")
	@Mapping(source = "type", target = "type")
	@Mapping(source = "movementValue", target = "movementValue")
	@Mapping(source = "balance", target = "balance")
	@Mapping(source = "account.id", target = "account.id")
	Movement toMovement(MovementModel movementModel);

	List<Movement> toMovements(List<MovementModel> movements);

	@InheritInverseConfiguration
	MovementModel toMovementModel(Movement movement);
}
