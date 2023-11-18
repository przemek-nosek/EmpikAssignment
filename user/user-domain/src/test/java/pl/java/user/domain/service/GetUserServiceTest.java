package pl.java.user.domain.service;


import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import pl.java.shared.out.client.AbstractClient;
import pl.java.shared.out.client.response.GithubUserResponse;
import pl.java.user.domain.port.out.UserCallCounterPort;

import java.time.LocalDateTime;

import static org.mockito.BDDMockito.anyString;
import static org.mockito.BDDMockito.given;

@ExtendWith(MockitoExtension.class)
class GetUserServiceTest {

    private static final String LOGIN = "user-login";
    private static final LocalDateTime CREATED_AT = LocalDateTime.of(2020, 10, 10, 10, 0, 0);

    @Mock
    private AbstractClient abstractClient;
    @Mock
    private UserCallCounterPort userCallCounterPort;
    @InjectMocks
    private GetUserService getUserService;

    @Test
    void getUser_shouldReturnUser_whenFoundAndFollowerCountNotEqualsZero() {
        //given
        given(abstractClient.getUserDetails(anyString())).willReturn(buildGithubUserResponse(10));
    }

    private GithubUserResponse buildGithubUserResponse(int followers) {
        return GithubUserResponse.builder()
                .id(1)
                .login(LOGIN)
                .name("firstName lastName")
                .type("type")
                .avatarUrl("avatarUrl")
                .createdAt(CREATED_AT)
                .publicRepos(10)
                .followers(followers)
                .build();
    }
}