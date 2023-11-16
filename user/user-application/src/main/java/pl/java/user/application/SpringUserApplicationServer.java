package pl.java.user.application;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Import;
import pl.java.shared.out.client.feign.FeignGithubServiceClient;
import pl.java.user.application.beans.UserBeans;

@ComponentScan(basePackages = {
        "pl.java.user.application",
        "pl.java.user.infrastructure"
})
@Import(UserBeans.class)
@SpringBootApplication
@EnableFeignClients(clients = FeignGithubServiceClient.class)
public class SpringUserApplicationServer {

    public static void main(String[] args) {
        SpringApplication.run(SpringUserApplicationServer.class, args);
    }
}
