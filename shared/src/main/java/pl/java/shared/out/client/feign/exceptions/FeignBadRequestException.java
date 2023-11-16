package pl.java.shared.out.client.feign.exceptions;

public class FeignBadRequestException extends RuntimeException {
    public FeignBadRequestException(String message) {
        super(message);
    }
}
