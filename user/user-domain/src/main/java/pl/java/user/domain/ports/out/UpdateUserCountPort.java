package pl.java.user.domain.ports.out;

import pl.java.user.domain.model.User;

public interface UpdateUserCountPort {

    void update(User user);
}
