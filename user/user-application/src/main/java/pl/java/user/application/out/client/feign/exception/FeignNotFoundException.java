package pl.java.user.application.out.client.feign.exception;

import pl.java.shared.exception.NotFoundException;

public class FeignNotFoundException extends NotFoundException {
    public FeignNotFoundException(String message) {
        super(message);
    }
}
