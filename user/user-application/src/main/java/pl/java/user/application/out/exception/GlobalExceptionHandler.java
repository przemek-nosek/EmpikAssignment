package pl.java.user.application.out.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import pl.java.shared.exception.BadRequestException;
import pl.java.shared.exception.NotFoundException;
import pl.java.user.application.out.client.feign.exception.FeignBadRequestException;
import pl.java.user.application.out.client.feign.exception.FeignNotFoundException;
import pl.java.user.application.out.exception.message.ErrorMessage;
import pl.java.user.domain.exception.DomainException;

import java.time.LocalDateTime;

@RestControllerAdvice

class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler({DomainException.class, FeignBadRequestException.class})
    ResponseEntity<ErrorMessage> handleDomainException(BadRequestException ex) {
        return new ResponseEntity<>(getErrorMessage(HttpStatus.BAD_REQUEST, ex.getMessage()), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(FeignNotFoundException.class)
    ResponseEntity<ErrorMessage> handleNotFoundException(NotFoundException ex) {
        return new ResponseEntity<>(getErrorMessage(HttpStatus.NOT_FOUND, ex.getMessage()), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(Throwable.class)
    ResponseEntity<ErrorMessage> handleOtherExceptions(Throwable ex) {
        return new ResponseEntity<>(getErrorMessage(HttpStatus.INTERNAL_SERVER_ERROR, ex.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    private ErrorMessage getErrorMessage(HttpStatus httpStatus, String message) {
        return new ErrorMessage(httpStatus, httpStatus.value(), LocalDateTime.now(), message);
    }
}
