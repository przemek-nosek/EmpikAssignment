package pl.java.user.infrastructure.spring.database.repository;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;
import pl.java.user.domain.repository.UserRepository;
import pl.java.user.infrastructure.spring.database.model.UserEntity;

@Repository
@Profile("jpa")
@RequiredArgsConstructor
public class JpaUserRepository implements UserRepository {

    private static final int ZERO_REQUEST_COUNT = 0;
    private final SpringDataJpaUserRepository springDataJpaUserRepository;

    @Override
    public int getRequestCount(String login) {
        return springDataJpaUserRepository.findById(login).map(UserEntity::getRequestCount)
                .orElse(ZERO_REQUEST_COUNT);
    }

    @Override
    public void updateRequestCount(String login, int count) {
        springDataJpaUserRepository.findById(login)
                .ifPresentOrElse(
                        userEntity -> springDataJpaUserRepository.save(userEntity.toBuilder()
                                .requestCount(count)
                                .build()),
                        () -> springDataJpaUserRepository.save(UserEntity.builder()
                                .login(login)
                                .requestCount(count)
                                .build()));
    }
}
