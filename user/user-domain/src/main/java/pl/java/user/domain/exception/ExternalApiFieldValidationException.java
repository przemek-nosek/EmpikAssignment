package pl.java.user.domain.exception;

public class ExternalApiFieldValidationException extends RuntimeException {

    public ExternalApiFieldValidationException(String message) {
        super(message);
    }
}
