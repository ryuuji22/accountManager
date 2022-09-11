package com.nttdata.account.infraestructure.persistance.mappers;

import java.util.List;

import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.nttdata.account.domain.entities.Role;
import com.nttdata.account.infraestructure.models.RoleModel;

@Mapper(componentModel = "spring")
public interface RoleMapper {

    @Mapping(source = "id", target = "id")
    @Mapping(source = "name", target = "name")
    Role toRole(RoleModel roleModel);

    List<Role> toRoles(List<RoleModel> roles);

    @InheritInverseConfiguration
    RoleModel toRoleModel(Role roles);
}
