package pl.java.user.domain.service;

import lombok.RequiredArgsConstructor;
import pl.java.shared.out.client.AbstractClient;
import pl.java.shared.out.client.response.GithubUserResponse;
import pl.java.shared.validation.Error;
import pl.java.user.domain.exception.ExternalApiFieldValidationException;
import pl.java.user.domain.exception.ExternalApiReturnNullException;
import pl.java.user.domain.exception.ZeroFollowersException;
import pl.java.user.domain.model.User;
import pl.java.user.domain.port.in.GetUserUseCase;
import pl.java.user.domain.port.out.UserCallCounterPort;

import static pl.java.user.domain.exception.messages.DomainErrorMessages.USER_FOLLOWERS_COUNT_IS_ZERO_CANT_DIVIDE;
import static pl.java.user.domain.exception.messages.DomainErrorMessages.USER_RETURNED_BY_API_IS_NULL;

@RequiredArgsConstructor
public class GetUserService implements GetUserUseCase {

    private final AbstractClient client;
    private final UserCallCounterPort userCallCounterPort;
    private static final int FIRST_CONSTANT = 6;
    private static final int SECOND_CONSTANT = 2;

    @Override
    public User getUser(String login) {
        GithubUserResponse userDetails = client.getUserDetails(login);
        if (null == userDetails) {
            throw new ExternalApiReturnNullException(String.format(USER_RETURNED_BY_API_IS_NULL.getMessage(), login));
        }
        User.apply(userDetails)
                .onFailure(error -> handleError(error, login))
                .onSuccess(unused -> handleSuccess(login));
        return toUser(userDetails);
    }

    private void handleSuccess(String login) {
        userCallCounterPort.update(login);
    }

    private void handleError(Error error, String login) {
        if (error.errorType() == USER_FOLLOWERS_COUNT_IS_ZERO_CANT_DIVIDE.getType()) {
            userCallCounterPort.update(login);
            throw new ZeroFollowersException(String.format(USER_FOLLOWERS_COUNT_IS_ZERO_CANT_DIVIDE.getMessage(), login));
        }
        throw new ExternalApiFieldValidationException(error.errorMessage());
    }

    private User toUser(GithubUserResponse userDetails) {
        return User.builder()
                .id(userDetails.id())
                .login(userDetails.login())
                .name(userDetails.name())
                .type(userDetails.type())
                .avatarUrl(userDetails.avatarUrl())
                .createdAt(userDetails.createdAt())
                .calculations(doCalculations(userDetails.followers(), userDetails.publicRepos()))
                .build();
    }

    private double doCalculations(int followers, int publicReposCount) {
        return (double) FIRST_CONSTANT / followers * (SECOND_CONSTANT + publicReposCount);
    }
}
