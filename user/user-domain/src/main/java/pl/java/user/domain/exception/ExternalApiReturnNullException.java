package pl.java.user.domain.exception;

public class ExternalApiReturnNullException extends RuntimeException {

    public ExternalApiReturnNullException(String message) {
        super(message);
    }
}
