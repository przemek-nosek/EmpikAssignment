package pl.java.user.infrastructure.adapter;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pl.java.user.domain.model.User;
import pl.java.user.domain.port.out.UserCallCounterPort;
import pl.java.user.domain.repository.UserRepository;


@Service
@RequiredArgsConstructor
public class UserCallCounterAdapter implements UserCallCounterPort {

    private final UserRepository userRepository;

    @Override
    public void update(User user) {
        int currentRequestCount = userRepository.getRequestCount(user.login());
        int updatedCount = currentRequestCount + 1;
        userRepository.updateRequestCount(user.login(), updatedCount);
    }
}
