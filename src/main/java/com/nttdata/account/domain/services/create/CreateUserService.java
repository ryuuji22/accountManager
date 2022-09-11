package com.nttdata.account.domain.services.create;

import java.util.List;

import com.nttdata.account.domain.entities.User;

import io.jkratz.mediator.core.Request;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreateUserService implements Request<User> {

    private String identification;
    private String password;
    private List<String> roles;

}
