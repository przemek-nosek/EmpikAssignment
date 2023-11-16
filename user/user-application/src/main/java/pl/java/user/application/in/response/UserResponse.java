package pl.java.user.application.in.response;

import lombok.Builder;

@Builder
public record UserResponse(
        long id,
        String login,
        String name,
        String type,
        String avatarUrl,
        String createdAt,
        double calculations
) {
}
