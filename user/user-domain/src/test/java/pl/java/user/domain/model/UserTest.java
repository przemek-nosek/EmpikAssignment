package pl.java.user.domain.model;

import org.junit.jupiter.api.Test;
import pl.java.shared.out.client.response.GithubUserResponse;
import pl.java.shared.validation.Error;
import pl.java.shared.validation.ValidationResult;

import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.assertThat;

class UserTest {

    private static final String LOGIN = "user-login";
    private static final LocalDateTime CREATED_AT = LocalDateTime.of(2020, 10, 10, 10, 0, 0);

    @Test
    void apply_GithubUserResponse_validationShouldReturnSuccess() {
        //given
        GithubUserResponse githubUserResponse = buildGithubUserResponse(LOGIN, 2);

        //when
        ValidationResult<Error, Void> result = User.apply(githubUserResponse);

        //then
        assertThat(result.isSuccess()).isTrue();
    }

    @Test
    void apply_GithubUserResponse_validationShouldReturnFailureLoginWasNull() {
        //given
        GithubUserResponse githubUserResponse = buildGithubUserResponse(null, 2);

        //when
        ValidationResult<Error, Void> result = User.apply(githubUserResponse);

        //then
        assertThat(result.isFailure()).isTrue();
        assertThat(result.getFailure()).isNotNull();
        assertThat(result.getFailure().errorMessage()).isEqualTo(String.format("Parameter: %s can not have value: %s", "login", null));
    }

    @Test
    void apply_GithubUserResponse_validationShouldReturnFailureFollowersWasZero() {
        //given
        GithubUserResponse githubUserResponse = buildGithubUserResponse(LOGIN, 0);

        //when
        ValidationResult<Error, Void> result = User.apply(githubUserResponse);

        //then
        assertThat(result.isFailure()).isTrue();
        assertThat(result.getFailure()).isNotNull();
        assertThat(result.getFailure().errorMessage()).isEqualTo(String.format("User's %s follower count is zero therefore can not divide.", LOGIN));
    }

    private GithubUserResponse buildGithubUserResponse(String login, int followers) {
        return GithubUserResponse.builder()
                .id(1)
                .login(login)
                .name("firstName lastName")
                .type("type")
                .avatarUrl("avatarUrl")
                .createdAt(CREATED_AT)
                .publicRepos(24)
                .followers(followers)
                .build();
    }
}