package pl.java.shared.out.client.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;

import java.time.LocalDateTime;

@Builder
public record GithubUserResponse(
        long id,
        String login,
        String name,
        String type,
        @JsonProperty("avatar_url")
        String avatarUrl,
        @JsonProperty("created_at")
        LocalDateTime createdAt,
        @JsonProperty("public_repos")
        int publicRepos,
        int followers
) {
}
