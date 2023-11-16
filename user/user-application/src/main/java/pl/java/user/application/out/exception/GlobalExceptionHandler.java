package pl.java.user.application.out.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import pl.java.shared.exception.BadRequestException;
import pl.java.shared.exception.NotFoundException;
import pl.java.user.application.out.client.feign.exception.FeignBadRequestException;
import pl.java.user.application.out.client.feign.exception.FeignNotFoundException;
import pl.java.user.application.out.exception.message.ErrorMessage;
import pl.java.user.domain.exception.DomainException;

import java.time.LocalDateTime;

@RestControllerAdvice
class GlobalExceptionHandler {

    @ExceptionHandler({DomainException.class, FeignBadRequestException.class})
    ResponseEntity<ErrorMessage> handleDomainException(BadRequestException ex) {
        return ResponseEntity.ok(getErrorMessage(HttpStatus.BAD_REQUEST, ex.getMessage()));
    }

    @ExceptionHandler(FeignNotFoundException.class)
    ResponseEntity<ErrorMessage> handleFeignNotFoundException(NotFoundException ex) {
        return ResponseEntity.ok(getErrorMessage(HttpStatus.NOT_FOUND, ex.getMessage()));
    }

    private ErrorMessage getErrorMessage(HttpStatus httpStatus, String message) {
        return new ErrorMessage(httpStatus, httpStatus.value(), LocalDateTime.now(), message);
    }
}
