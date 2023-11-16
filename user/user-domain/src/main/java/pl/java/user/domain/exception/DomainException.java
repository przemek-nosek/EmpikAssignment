package pl.java.user.domain.exception;

import pl.java.shared.exception.BadRequestException;

public class DomainException extends BadRequestException {

    public DomainException(String message) {
        super(message);
    }
}
