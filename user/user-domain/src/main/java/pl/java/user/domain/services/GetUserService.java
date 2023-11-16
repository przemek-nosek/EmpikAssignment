package pl.java.user.domain.services;

import pl.java.shared.out.client.AbstractClient;
import pl.java.shared.out.client.GithubServiceClient;
import pl.java.shared.out.client.response.GithubUserResponse;
import pl.java.user.domain.model.User;
import pl.java.user.domain.ports.in.GetUserUseCase;

public record GetUserService(
        AbstractClient client

) implements GetUserUseCase {

    @Override
    public User getUser(String login) {
        GithubUserResponse userDetails = client.getUserDetails(login);
    }


}
