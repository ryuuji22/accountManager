package com.devsu.account.domain.interfaces.repositories;

import java.util.List;
import java.util.Optional;

import com.devsu.account.domain.entities.User;

public interface IUserRepository {

    Optional<User> getByIdentification(String identification);

    Optional<List<User>> getAll();

    User create(User user);
    
    void update(User user);

    void delete(User user);
}
