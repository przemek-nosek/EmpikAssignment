package pl.java.user.infrastructure.spring.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import pl.java.shared.out.client.feign.exceptions.FeignBadRequestException;
import pl.java.shared.out.client.feign.exceptions.FeignNotFoundException;
import pl.java.user.domain.exception.DomainException;
import pl.java.user.infrastructure.spring.exception.message.ErrorMessage;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.Collections;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler({DomainException.class, FeignBadRequestException.class})
    public <T extends RuntimeException> ResponseEntity<ErrorMessage> handleDomainException(T ex) {
        return ResponseEntity.ok(getErrorMessage(HttpStatus.BAD_REQUEST, ex.getMessage()));
    }

    @ExceptionHandler(FeignNotFoundException.class)
    public ResponseEntity<ErrorMessage> handleFeignNotFoundException(FeignNotFoundException ex) {
        return ResponseEntity.ok(getErrorMessage(HttpStatus.NOT_FOUND, ex.getMessage()));
    }

    private ErrorMessage getErrorMessage(HttpStatus httpStatus, String message) {
        return new ErrorMessage(httpStatus, httpStatus.value(), LocalDateTime.now(), message);
    }
}
