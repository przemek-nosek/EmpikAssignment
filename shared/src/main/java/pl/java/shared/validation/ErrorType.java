package pl.java.shared.validation;

public interface ErrorType {

    String getMessage();

    default ErrorType getType() {
        return this;
    }
}
