package pl.java.shared.exception;

public abstract class BadRequestException extends RuntimeException {

    protected BadRequestException(String message) {
        super(message);
    }
}
