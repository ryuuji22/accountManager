package com.devsu.account.domain.services.validations;

import io.jkratz.mediator.core.Request;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class ValidateIdentificationCIService implements Request<Boolean> {
    private String ciNumber;

    public ValidateIdentificationCIService(String ciNumber) {
        this.ciNumber = ciNumber;
    }
}
