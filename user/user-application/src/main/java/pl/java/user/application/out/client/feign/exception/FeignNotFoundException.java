package pl.java.user.application.out.client.feign.exception;

public class FeignNotFoundException extends RuntimeException {
    public FeignNotFoundException(String message) {
        super(message);
    }
}
