package pl.java.user.infrastructure.jpa.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.java.user.infrastructure.jpa.model.UserEntity;

import java.util.Optional;

interface SpringDataJpaUserRepository extends JpaRepository<UserEntity, String> {

    Optional<UserEntity> findByLogin(String login);
}
