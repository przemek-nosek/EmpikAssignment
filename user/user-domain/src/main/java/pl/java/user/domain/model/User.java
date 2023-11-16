package pl.java.user.domain.model;

import lombok.Builder;

import java.time.LocalDateTime;


@Builder(toBuilder = true)
public record User(
        long id,
        String login,
        String name,
        String type,
        String avatarUrl,
        LocalDateTime createdAt,
        double calculations
) {
}