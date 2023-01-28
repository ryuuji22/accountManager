package com.devsu.account.application.queries;

import com.devsu.account.domain.entities.User;

import io.jkratz.mediator.core.Request;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class ReadUserDetailsQuery implements Request<User> {


}
