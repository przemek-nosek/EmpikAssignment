package pl.java.user.infrastructure.spring.database.postgresql;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.java.user.infrastructure.spring.database.model.UserEntity;

public interface JpaUserRepository extends JpaRepository<UserEntity, String> {
}
