package pl.java.user.infrastructure.spring.beans;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import pl.java.shared.out.client.AbstractClient;
import pl.java.user.domain.ports.in.GetUserUseCase;
import pl.java.user.domain.ports.out.UpdateUserCountPort;
import pl.java.user.domain.services.GetUserService;

@Configuration
public class UserBeans {

    @Bean
    public GetUserUseCase getUserUseCase(AbstractClient abstractClient, UpdateUserCountPort updateUserCountPort) {
        return new GetUserService(abstractClient, updateUserCountPort);
    }
}
