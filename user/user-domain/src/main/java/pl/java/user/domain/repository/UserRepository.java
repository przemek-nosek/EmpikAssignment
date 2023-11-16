package pl.java.user.domain.repository;

import pl.java.user.domain.model.User;

public interface UserRepository {

    void saveUser(User user);
}
