package pl.java.user.infrastructure.spring.database.postgresql;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;
import pl.java.user.domain.model.User;
import pl.java.user.domain.ports.out.UpdateUserCountPort;
import pl.java.user.infrastructure.spring.database.model.UserEntity;

@Profile("jpa")
@Service
public record UpdateUserCountAdapter(
        JpaUserRepository jpaUserRepository
) implements UpdateUserCountPort {

    private static final int STARTING_COUNT = 1;

    @Override
    public void update(User user) {
        UserEntity entity = jpaUserRepository.findById(user.login())
                .map(this::updateCount)
                .orElse(toUserEntity(user));

        jpaUserRepository.save(entity);
    }

    private UserEntity toUserEntity(User user) {
        return UserEntity.builder()
                .login(user.login())
                .requestCount(STARTING_COUNT)
                .build();
    }

    private UserEntity updateCount(UserEntity userEntity) {
        int newRequestCount = userEntity.getRequestCount() + 1;
        return userEntity.toBuilder()
                .requestCount(newRequestCount)
                .build();
    }
}
