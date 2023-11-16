package pl.java.shared.out.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import pl.java.shared.out.client.response.GithubUserResponse;

@FeignClient(name = "githubServiceClient", url = "https://api.github.com/users")//todo
public interface GithubServiceClient extends AbstractClient {

    @Override
    @GetMapping(path = "/{login}")
    GithubUserResponse getUserDetails(@PathVariable String login);
}
