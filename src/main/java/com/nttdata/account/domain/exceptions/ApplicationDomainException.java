package com.nttdata.account.domain.exceptions;

public class ApplicationDomainException extends RuntimeException {

    private static final long serialVersionUID = -7807343563304598095L;

	public ApplicationDomainException() {
        super();
    }

    public ApplicationDomainException(final String message, final Throwable cause) {
        super(message, cause);
    }

    public ApplicationDomainException(final String message) {
        super(message);
    }

    public ApplicationDomainException(final Throwable cause) {
        super(cause);
    }
}
