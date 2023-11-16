package pl.java.user.infrastructure.spring.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import pl.java.user.domain.exception.DomainException;
import pl.java.user.infrastructure.spring.exception.message.ErrorMessage;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.Collections;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(DomainException.class)
    public ResponseEntity<ErrorMessage> handleDomainException(DomainException ex) {
        return ResponseEntity.ok(getErrorMessage(HttpStatus.BAD_REQUEST, ex.getMessage(), Collections.emptyList()));
    }

    private ErrorMessage getErrorMessage(HttpStatus httpStatus, String message, Collection<String> errors) {
        return new ErrorMessage(httpStatus, httpStatus.value(), LocalDateTime.now(), message, errors);
    }
}
