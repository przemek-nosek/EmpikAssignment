package pl.java.shared.out.client.feign.exceptions;

import pl.java.shared.exception.NotFoundException;

public class FeignNotFoundException extends NotFoundException {
    public FeignNotFoundException(String message) {
        super(message);
    }
}
