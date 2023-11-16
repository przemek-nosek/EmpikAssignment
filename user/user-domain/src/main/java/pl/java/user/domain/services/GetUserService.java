package pl.java.user.domain.services;

import pl.java.shared.out.client.AbstractClient;
import pl.java.shared.out.client.response.GithubUserResponse;
import pl.java.user.domain.exception.DomainException;
import pl.java.user.domain.model.User;
import pl.java.user.domain.ports.in.GetUserUseCase;
import pl.java.user.domain.ports.out.UserCallCounterPort;

import static pl.java.user.domain.exception.messages.DomainErrorMessages.USER_FOLLOWERS_COUNT_IS_ZERO_CANT_DIVIDE;

public record GetUserService(
        AbstractClient client,
        UserCallCounterPort userCallCounterPort) implements GetUserUseCase {
    private static final int FIRST_CONSTANT = 6;
    private static final int SECOND_CONSTANT = 2;

    @Override
    public User getUser(String login) {
        GithubUserResponse userDetails = client.getUserDetails(login);

        if (0 == userDetails.followers()) {
            throw new DomainException(String.format(USER_FOLLOWERS_COUNT_IS_ZERO_CANT_DIVIDE.getMessage(), login));
        }

        double calculations = doCalculations(userDetails.followers(), userDetails.publicRepos());

        User user = toUser(userDetails, calculations);
        userCallCounterPort.update(user);
        return user;
    }

    private User toUser(GithubUserResponse userDetails, double calculations) {
        return User.builder()
                .id(userDetails.id())
                .login(userDetails.login())
                .name(userDetails.name())
                .type(userDetails.type())
                .avatarUrl(userDetails.avatarUrl())
                .createdAt(userDetails.createdAt())
                .calculations(calculations)
                .build();
    }

    private double doCalculations(int followers, int publicReposCount) {
        return (double) FIRST_CONSTANT / followers * (SECOND_CONSTANT + publicReposCount);
    }
}
