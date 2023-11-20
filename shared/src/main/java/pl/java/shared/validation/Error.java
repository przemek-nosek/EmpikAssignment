package pl.java.shared.validation;

public record Error(ErrorType errorType, String errorMessage) {

    public static Error format(ErrorType errorType, String errorMessage, Object... args) {
        return new Error(errorType, String.format(errorMessage, args));
    }
}
