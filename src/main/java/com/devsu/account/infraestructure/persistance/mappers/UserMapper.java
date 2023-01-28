package com.devsu.account.infraestructure.persistance.mappers;

import java.util.List;

import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.devsu.account.domain.entities.User;
import com.devsu.account.infraestructure.models.UserModel;

@Mapper(componentModel = "spring",uses = {RoleMapper.class})
public interface UserMapper {

    @Mapping(source = "id", target = "id")
    @Mapping(source = "identification", target = "identification")
    @Mapping(source = "password", target = "password")
    @Mapping(source = "enabled", target = "enabled")
    @Mapping(source = "createdAt", target = "createdAt")
    @Mapping(source = "roles", target = "roles")
    User toUser(UserModel userModel);

    List<User> toUsers(List<UserModel> users);

    @InheritInverseConfiguration
    UserModel toUserModel(User user);
}
