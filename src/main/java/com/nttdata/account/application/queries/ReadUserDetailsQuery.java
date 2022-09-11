package com.nttdata.account.application.queries;

import com.nttdata.account.domain.entities.User;

import io.jkratz.mediator.core.Request;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class ReadUserDetailsQuery implements Request<User> {


}
