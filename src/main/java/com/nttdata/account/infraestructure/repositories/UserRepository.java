/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.nttdata.account.infraestructure.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.nttdata.account.domain.entities.User;
import com.nttdata.account.domain.interfaces.repositories.IUserRepository;
import com.nttdata.account.infraestructure.models.UserModel;
import com.nttdata.account.infraestructure.persistance.mappers.UserMapper;
import com.nttdata.account.infraestructure.repositories.jpa.IJpaUserRepository;

/**
 *
 * @author diego
 */
@Repository
public class UserRepository implements IUserRepository {

    @Autowired
    private IJpaUserRepository jpaUserRepository;

    @Autowired
    private UserMapper mapper;

    @Override
    public Optional<List<User>> getAll() {
        return jpaUserRepository.findByEnabled(Boolean.TRUE)
                .map(mapper::toUsers);
    }

    @Override
    public Optional<User> getByIdentification(String identification) {
        return jpaUserRepository.findByIdentificationAndEnabled(identification, Boolean.TRUE)
                .map(mapper::toUser);
    }

    @Override
    public User create(User user) {
        UserModel userModel = mapper.toUserModel(user);
        return mapper.toUser(jpaUserRepository.save(userModel));
    }

    @Override
    public void delete(User user) {
        user.setEnabled(Boolean.FALSE);
        this.jpaUserRepository.save(mapper.toUserModel(user));
    }
    
    @Override
	public void update(User user) {
		this.jpaUserRepository.save(mapper.toUserModel(user));
	}

}
