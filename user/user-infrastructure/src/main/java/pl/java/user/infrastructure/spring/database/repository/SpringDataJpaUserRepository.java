package pl.java.user.infrastructure.spring.database.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.java.user.infrastructure.spring.database.model.UserEntity;

public interface SpringDataJpaUserRepository extends JpaRepository<UserEntity, String> {

}
