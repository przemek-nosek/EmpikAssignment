package pl.java.user.infrastructure.spring.database.config;


import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@Configuration
@EnableJpaRepositories(basePackages = "pl.java.user.infrastructure")
@EntityScan(basePackages = "pl.java.user.infrastructure")
public class JpaConfig {
}
