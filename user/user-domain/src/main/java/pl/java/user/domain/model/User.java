package pl.java.user.domain.model;

import lombok.Builder;
import lombok.RequiredArgsConstructor;
import pl.java.shared.out.client.response.GithubUserResponse;
import pl.java.shared.validation.Error;
import pl.java.shared.validation.ValidationResult;

import java.time.LocalDateTime;
import java.util.function.Supplier;

import static pl.java.shared.validation.DataValidator.combine;
import static pl.java.shared.validation.DataValidator.validateIsNotNull;
import static pl.java.user.domain.exception.messages.DomainErrorMessages.USER_FOLLOWERS_COUNT_IS_ZERO_CANT_DIVIDE;


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

    public static ValidationResult<Error, Void> apply(GithubUserResponse response) {
        return Validator.validate(response);
    }

    @RequiredArgsConstructor
    private static class Validator {

        private static ValidationResult<Error, Void> validate(GithubUserResponse response) {
            return combine(
                    validateIsNotNull("login", response.login()),
                    validateFollowersIsNotZero(response.followers(), response.login())
            );
        }

        private static Supplier<ValidationResult<Error, Void>> validateFollowersIsNotZero(int followers, String login) {
            return () -> followers == 0
                    ? ValidationResult.failure(
                    Error.format(USER_FOLLOWERS_COUNT_IS_ZERO_CANT_DIVIDE.getType(),
                            USER_FOLLOWERS_COUNT_IS_ZERO_CANT_DIVIDE.getMessage(), login))
                    : ValidationResult.success();
        }
    }
}