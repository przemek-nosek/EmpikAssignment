package pl.java.shared.out.client.feign.exceptions;

public class FeignNotFoundException extends RuntimeException {
    public FeignNotFoundException(String message) {
        super(message);
    }
}
