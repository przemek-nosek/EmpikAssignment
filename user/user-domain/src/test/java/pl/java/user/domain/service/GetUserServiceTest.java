package pl.java.user.domain.service;


import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import pl.java.shared.out.client.AbstractClient;
import pl.java.shared.out.client.response.GithubUserResponse;
import pl.java.user.domain.exception.ExternalApiFieldValidationException;
import pl.java.user.domain.exception.ExternalApiReturnNullException;
import pl.java.user.domain.exception.ZeroFollowersException;
import pl.java.user.domain.model.User;
import pl.java.user.domain.port.out.UserCallCounterPort;

import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.BDDMockito.*;

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
        given(abstractClient.getUserDetails(anyString())).willReturn(buildGithubUserResponse(LOGIN, 2));
        willDoNothing().given(userCallCounterPort).update(anyString());

        //when
        User actual = getUserService.getUser(LOGIN);

        //then
        then(abstractClient).should().getUserDetails(anyString());
        then(userCallCounterPort).should().update(anyString());
        assertThat(actual)
                .usingRecursiveComparison()
                .isEqualTo(buildExpectedResponse());
    }

    @Test
    void getUser_shouldThrowExternalApiReturnNullException_whenApiResponseWasNull() {
        //given
        given(abstractClient.getUserDetails(anyString())).willReturn(null);

        //when
        //then
        assertThatThrownBy(() -> getUserService.getUser(LOGIN))
                .hasMessage(String.format("User %s returned by API was null, can not process.", LOGIN))
                .isInstanceOf(ExternalApiReturnNullException.class);
        then(abstractClient).should().getUserDetails(anyString());
        then(userCallCounterPort).should(never()).update(anyString());
    }

    @Test
    void getUser_shouldThrowZeroFollowersException_whenUserFollowersIsZero() {
        //given
        given(abstractClient.getUserDetails(anyString())).willReturn(buildGithubUserResponse(LOGIN, 0));

        //when
        //then
        assertThatThrownBy(() -> getUserService.getUser(LOGIN))
                .hasMessage(String.format("User's %s follower count is zero therefore can not divide.", LOGIN))
                .isInstanceOf(ZeroFollowersException.class);
        then(abstractClient).should().getUserDetails(anyString());
        then(userCallCounterPort).should().update(anyString());
    }

    @Test
    void getUser_shouldThrowExternalApiFieldValidationException_whenUserLoginWasNull() {
        //given
        given(abstractClient.getUserDetails(anyString())).willReturn(buildGithubUserResponse(null, 0));

        //when
        //then
        assertThatThrownBy(() -> getUserService.getUser(LOGIN))
                .hasMessage(String.format("Parameter: %s can not have value: %s", "login", null))
                .isInstanceOf(ExternalApiFieldValidationException.class);
        then(abstractClient).should().getUserDetails(anyString());
        then(userCallCounterPort).should(never()).update(anyString());
    }

    private GithubUserResponse buildGithubUserResponse(String login, int followers) {
        return GithubUserResponse.builder()
                .id(1)
                .login(login)
                .name("firstName lastName")
                .type("type")
                .avatarUrl("avatarUrl")
                .createdAt(CREATED_AT)
                .publicRepos(24)
                .followers(followers)
                .build();
    }

    private User buildExpectedResponse() {
        return User.builder()
                .id(1)
                .login(LOGIN)
                .name("firstName lastName")
                .type("type")
                .avatarUrl("avatarUrl")
                .createdAt(CREATED_AT)
                .calculations(78)
                .build();
    }
}