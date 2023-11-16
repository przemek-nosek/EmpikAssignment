package pl.java.user.infrastructure.spring.database;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;
import pl.java.user.domain.model.User;
import pl.java.user.domain.ports.out.UserCallCounterPort;
import pl.java.user.domain.repository.UserRepository;

@Profile("jpa")
@Service
public record UserCallCounterAdapter(
        UserRepository userRepository) implements UserCallCounterPort {

    @Override
    public void update(User user) {
        int currentRequestCount = userRepository.getRequestCount(user.login());
        int updatedCount = currentRequestCount + 1;
        userRepository.updateRequestCount(user.login(), updatedCount);
    }
}
