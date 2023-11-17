package pl.java.user.application.bean;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import pl.java.shared.out.client.AbstractClient;
import pl.java.user.domain.port.in.GetUserUseCase;
import pl.java.user.domain.port.out.UserCallCounterPort;
import pl.java.user.domain.service.GetUserService;

@Configuration
public class UserBeans {

    @Bean
    public GetUserUseCase getUserUseCase(AbstractClient abstractClient, UserCallCounterPort userCallCounterPort) {
        return new GetUserService(abstractClient, userCallCounterPort);
    }
}
