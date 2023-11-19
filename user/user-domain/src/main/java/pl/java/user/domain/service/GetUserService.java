package pl.java.user.domain.service;

import lombok.RequiredArgsConstructor;
import pl.java.shared.out.client.AbstractClient;
import pl.java.shared.out.client.response.GithubUserResponse;
import pl.java.user.domain.exception.ZeroFollowersException;
import pl.java.user.domain.model.User;
import pl.java.user.domain.port.in.GetUserUseCase;
import pl.java.user.domain.port.out.UserCallCounterPort;

import static pl.java.user.domain.exception.messages.DomainErrorMessages.USER_FOLLOWERS_COUNT_IS_ZERO_CANT_DIVIDE;

@RequiredArgsConstructor
public class GetUserService implements GetUserUseCase {

    private final AbstractClient client;
    private final UserCallCounterPort userCallCounterPort;
    private static final int FIRST_CONSTANT = 6;
    private static final int SECOND_CONSTANT = 2;

    @Override
    public User getUser(String login) {
        GithubUserResponse userDetails = client.getUserDetails(login);
        if (0 == userDetails.followers()) {
            userCallCounterPort.update(login);
            throw new ZeroFollowersException(String.format(USER_FOLLOWERS_COUNT_IS_ZERO_CANT_DIVIDE.getMessage(), login));
        }
        double calculations = doCalculations(userDetails.followers(), userDetails.publicRepos());
        User user = toUser(userDetails, calculations);
        userCallCounterPort.update(login);
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
