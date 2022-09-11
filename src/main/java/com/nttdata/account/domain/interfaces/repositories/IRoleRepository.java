package com.nttdata.account.domain.interfaces.repositories;

import java.util.Optional;

import com.nttdata.account.domain.entities.Role;

public interface IRoleRepository {

    Optional<Role> getByName(String name);

	void create(Role role);


}
