package pl.java.shared.out.client.feign.exceptions;

import pl.java.shared.exception.BadRequestException;

public class FeignBadRequestException extends BadRequestException {
    public FeignBadRequestException(String message) {
        super(message);
    }
}
