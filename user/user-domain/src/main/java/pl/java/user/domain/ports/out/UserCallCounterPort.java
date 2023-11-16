package pl.java.user.domain.ports.out;

import pl.java.user.domain.model.User;

public interface UserCallCounterPort {

    void update(User user);
}
