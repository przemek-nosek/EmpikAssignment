package pl.java.user.application.in.rest;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MvcResult;
import pl.java.shared.out.client.AbstractClient;
import pl.java.shared.out.client.response.GithubUserResponse;
import pl.java.user.application.in.response.UserResponse;
import pl.java.user.application.out.client.feign.exception.FeignNotFoundException;
import pl.java.user.domain.exception.ZeroFollowersException;

import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

class UserControllerIntegrationTest extends PostgreSQLContainerSetUp {

    private static final String LOGIN = "login";
    private static final String BASE_URL = "/users";
    private static final String LOGIN_URL = BASE_URL + "/{login}";
    private static final LocalDateTime DATE_IN_PAST = LocalDateTime.of(2020, 10, 10, 10, 0, 0);

    @MockBean
    private AbstractClient abstractClient;

    @Test
    void getUser_shouldReturnUserResponse_whenUserWasFoundAndHisFollowerCountIsGreaterThanZero() throws Exception {
        //given
        int followersCount = 2;
        given(abstractClient.getUserDetails(anyString())).willReturn(buildGithubUserResponse(followersCount));

        //when
        MvcResult mvcResult = mockMvc.perform(get(LOGIN_URL, LOGIN)
                        .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andReturn();


        //then
        UserResponse actual = objectMapper.readValue(mvcResult.getResponse().getContentAsString(), UserResponse.class);

        then(abstractClient).should().getUserDetails(anyString());
        assertThat(actual)
                .usingRecursiveComparison()
                .isEqualTo(buildExpectedResponse());
    }

    @Test
    void getUser_shouldThrowZeroFollowersException_whenUserWasFoundAndHisFollowerCountIsZero() throws Exception {
        //given
        int followersCount = 0;
        String expectedMessage = "User's login follower count is zero therefore can not divide.";
        given(abstractClient.getUserDetails(anyString())).willReturn(buildGithubUserResponse(followersCount));

        //when
        //then
        mockMvc.perform(get(LOGIN_URL, LOGIN)
                        .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isUnprocessableEntity())
                .andExpect(result -> assertThat(result.getResolvedException()).isInstanceOf(ZeroFollowersException.class))
                .andExpect(result -> assertThat(result.getResolvedException()).hasMessage(expectedMessage));

        then(abstractClient).should().getUserDetails(anyString());
    }

    @Test
    void getUser_shouldThrowFeignNotFoundException_whenUserWasNotFound() throws Exception {
        //given
        String expectedMessage = String.format("URL %s not found.", LOGIN);
        given(abstractClient.getUserDetails(anyString())).willThrow(new FeignNotFoundException(expectedMessage));

        //when
        //then
        mockMvc.perform(get(LOGIN_URL, LOGIN)
                        .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isNotFound())
                .andExpect(result -> assertThat(result.getResolvedException()).isInstanceOf(FeignNotFoundException.class))
                .andExpect(result -> assertThat(result.getResolvedException()).hasMessage(expectedMessage));

        then(abstractClient).should().getUserDetails(anyString());
    }

    private UserResponse buildExpectedResponse() {
        return UserResponse.builder()
                .id(123)
                .name("name")
                .login(LOGIN)
                .type("type")
                .avatarUrl("avatarUrl")
                .createdAt(DATE_IN_PAST)
                .calculations(24)
                .build();
    }

    private GithubUserResponse buildGithubUserResponse(int followersCount) {
        return GithubUserResponse.builder()
                .id(123)
                .name("name")
                .login(LOGIN)
                .followers(followersCount)
                .publicRepos(6)
                .type("type")
                .avatarUrl("avatarUrl")
                .createdAt(DATE_IN_PAST)
                .build();
    }
}