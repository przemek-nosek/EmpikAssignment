package pl.java.shared.out.client.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import pl.java.shared.out.client.AbstractClient;
import pl.java.shared.out.client.response.GithubUserResponse;

@FeignClient(name = "feignGithubServiceClient", url = "${github.client.url}", configuration = FeignClientErrorDecoder.class)
public interface FeignGithubServiceClient extends AbstractClient {

    @Override
    @GetMapping(path = "/{login}")
    GithubUserResponse getUserDetails(@PathVariable String login);
}
