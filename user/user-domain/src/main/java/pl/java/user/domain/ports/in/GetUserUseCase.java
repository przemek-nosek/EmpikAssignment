package pl.java.user.domain.ports.in;

import pl.java.user.domain.model.User;

public interface GetUserUseCase {

    User getUser(String login);
}
