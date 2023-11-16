package pl.java.user.domain.model;

import lombok.Builder;
import lombok.Getter;


@Builder(toBuilder = true)
public record User(
        long id,
        String login,
        String name,
        String type,
        String avatarUrl,
        String createdAt,
        int calculations
) {
}
