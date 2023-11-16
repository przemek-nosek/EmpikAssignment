package pl.java.user.infrastructure.jpa.repository;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;
import pl.java.user.domain.repository.UserRepository;
import pl.java.user.infrastructure.jpa.model.UserEntity;

@Repository
@Profile("jpa")
@RequiredArgsConstructor
class JpaUserRepository implements UserRepository {

    private static final int ZERO_REQUEST_COUNT = 0;
    private final SpringDataJpaUserRepository springDataJpaUserRepository;

    @Override
    public int getRequestCount(String login) {
        return springDataJpaUserRepository.findByLogin(login).map(UserEntity::getRequestCount)
                .orElse(ZERO_REQUEST_COUNT);
    }

    @Override
    public void updateRequestCount(String login, int count) {
        springDataJpaUserRepository.findByLogin(login)
                .ifPresentOrElse(
                        userEntity -> saveExistingUser(count, userEntity),
                        () -> saveNewUser(login, count));
    }

    private void saveNewUser(String login, int count) {
        springDataJpaUserRepository.save(UserEntity.builder()
                .login(login)
                .requestCount(count)
                .build());
    }

    private void saveExistingUser(int count, UserEntity userEntity) {
        springDataJpaUserRepository.save(userEntity.toBuilder()
                .requestCount(count)
                .build());
    }
}
