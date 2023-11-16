package pl.java.user.application.beans;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import pl.java.shared.out.client.AbstractClient;
import pl.java.user.domain.ports.in.GetUserUseCase;
import pl.java.user.domain.ports.out.UserCallCounterPort;

import pl.java.user.domain.services.GetUserService;

@Configuration
public class UserBeans {

    @Bean
    public GetUserUseCase getUserUseCase(AbstractClient abstractClient, UserCallCounterPort userCallCounterPort) {
        return new GetUserService(abstractClient, userCallCounterPort);
    }
}
