package pl.java.user.application.out.client.feign.exception;

public class FeignBadRequestException extends RuntimeException {
    public FeignBadRequestException(String message) {
        super(message);
    }
}
