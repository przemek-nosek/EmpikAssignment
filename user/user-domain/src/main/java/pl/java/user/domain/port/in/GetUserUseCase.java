package pl.java.user.domain.port.in;

import pl.java.user.domain.model.User;

public interface GetUserUseCase {

    User getUser(String login);
}
