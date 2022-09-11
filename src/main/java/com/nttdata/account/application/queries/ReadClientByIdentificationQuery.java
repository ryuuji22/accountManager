package com.nttdata.account.application.queries;

import com.nttdata.account.domain.entities.Client;

import io.jkratz.mediator.core.Request;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class ReadClientByIdentificationQuery implements Request<Client> {


}
