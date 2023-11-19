package pl.java.user.application.in.response;

import lombok.Builder;

import java.time.LocalDateTime;

@Builder
public record UserResponse(
        long id,
        String login,
        String name,
        String type,
        String avatarUrl,
        LocalDateTime createdAt,
        double calculations
) {
}
