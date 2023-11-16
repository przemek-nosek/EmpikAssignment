package pl.java.user.infrastructure.spring.exception.message;


import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;
import java.util.Collection;

@Builder
public record ErrorMessage(
        HttpStatus httpStatus,
        int statusCode,
        LocalDateTime timeStamp,
        String message) {
}