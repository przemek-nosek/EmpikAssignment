package pl.java.user.application.out.client.feign.exception;

import pl.java.shared.exception.BadRequestException;

public class FeignBadRequestException extends BadRequestException {
    public FeignBadRequestException(String message) {
        super(message);
    }
}
