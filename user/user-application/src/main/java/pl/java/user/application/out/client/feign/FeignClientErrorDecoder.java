package pl.java.user.application.out.client.feign;

import feign.Response;
import feign.codec.ErrorDecoder;
import pl.java.user.application.out.client.feign.exception.FeignBadRequestException;
import pl.java.user.application.out.client.feign.exception.FeignNotFoundException;


class FeignClientErrorDecoder implements ErrorDecoder {

    private static final String NOT_FOUND_ERROR = "URL %s not found.";
    private static final String BAD_REQUEST_ERROR = "URL %s is incorrect.";
    private final ErrorDecoder errorDecoder = new Default();

    @Override
    public Exception decode(String methodKey, Response response) {
        return switch (response.status()) {
            case 400 -> new FeignBadRequestException(String.format(BAD_REQUEST_ERROR, response.request().url()));
            case 404 -> new FeignNotFoundException(String.format(NOT_FOUND_ERROR, response.request().url()));
            default -> errorDecoder.decode(methodKey, response);
        };
    }
}
