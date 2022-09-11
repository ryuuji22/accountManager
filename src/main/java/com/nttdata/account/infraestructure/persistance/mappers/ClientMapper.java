package com.nttdata.account.infraestructure.persistance.mappers;

import java.util.List;

import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.nttdata.account.domain.entities.Client;
import com.nttdata.account.infraestructure.models.ClientModel;

@Mapper(componentModel = "spring", uses = {UserMapper.class})
public interface ClientMapper {

	@Mapping(source = "id", target = "id")
	@Mapping(source = "identification", target = "identification")
	@Mapping(source = "names", target = "names")
	@Mapping(source = "email", target = "email")
	@Mapping(source = "address", target = "address")
	@Mapping(source = "phone", target = "phone")
	@Mapping(source = "age", target = "age")
	@Mapping(source = "enabled", target = "enabled")
	@Mapping(source = "user", target = "user")
	@Mapping(source = "createdAt", target = "createdAt")
	Client toClient(ClientModel clientModel);

	List<Client> toClients(List<ClientModel> clients);

	@InheritInverseConfiguration
	ClientModel toClientModel(Client client);
}
