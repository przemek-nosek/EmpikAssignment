package pl.java.user.application;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Import;
import pl.java.user.application.bean.UserBeans;
import pl.java.user.application.out.client.feign.FeignGithubServiceClient;

@ComponentScan(basePackages = {
        "pl.java.user.application",
        "pl.java.user.infrastructure",
        "pl.java.shared"
})
@Import(UserBeans.class)
@SpringBootApplication
@EnableFeignClients(clients = FeignGithubServiceClient.class)
class SpringUserApplicationServer {

    public static void main(String[] args) {
        SpringApplication.run(SpringUserApplicationServer.class, args);
    }
}
//todo: create database if not exists!!
//todo: docker-compose
//todo: service test