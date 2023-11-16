package pl.java.shared.out.client.response;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.time.LocalDateTime;

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
