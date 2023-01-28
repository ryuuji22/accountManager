/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.devsu.account.infraestructure.repositories;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.devsu.account.domain.entities.Role;
import com.devsu.account.domain.interfaces.repositories.IRoleRepository;
import com.devsu.account.infraestructure.models.RoleModel;
import com.devsu.account.infraestructure.persistance.mappers.RoleMapper;
import com.devsu.account.infraestructure.repositories.jpa.IJpaRoleRepository;

/**
 *
 * @author diego
 */
@Repository
public class RoleRepository implements IRoleRepository {

	@Autowired
	private IJpaRoleRepository jpaRoleRepository;

	@Autowired
	private RoleMapper mapper;

	@Override
	public Optional<Role> getByName(String name) {
		return jpaRoleRepository.findByName(name).map(mapper::toRole);
	}

	@Override
	public void create(Role role) {
		RoleModel roleModel = mapper.toRoleModel(role);
		jpaRoleRepository.save(roleModel);
	}

}
