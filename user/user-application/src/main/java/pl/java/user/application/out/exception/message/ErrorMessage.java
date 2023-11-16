package pl.java.user.application.out.exception.message;


import lombok.Builder;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;

@Builder
public record ErrorMessage(
        HttpStatus httpStatus,
        int statusCode,
        LocalDateTime timeStamp,
        String message) {
}