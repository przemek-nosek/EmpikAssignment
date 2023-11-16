package pl.java.user.domain.repository;

public interface UserRepository {
    int getRequestCount(String login);

    void updateRequestCount(String login, int count);
}
