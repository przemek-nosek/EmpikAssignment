package pl.java.shared.out.client;

import org.springframework.web.bind.annotation.PathVariable;
import pl.java.shared.out.client.response.GithubUserResponse;

public interface AbstractClient {

    GithubUserResponse getUserDetails(@PathVariable String login);
}
