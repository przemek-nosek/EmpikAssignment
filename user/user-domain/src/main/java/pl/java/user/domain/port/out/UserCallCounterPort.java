package pl.java.user.domain.port.out;

import pl.java.user.domain.model.User;

public interface UserCallCounterPort {

    void update(String login);
}
