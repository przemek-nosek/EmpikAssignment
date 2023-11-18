package pl.java.user.application.in.rest;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MvcResult;
import pl.java.shared.out.client.AbstractClient;
import pl.java.shared.out.client.response.GithubUserResponse;

import java.time.LocalDateTime;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

class UserControllerIntegrationTest extends PostgreSQLContainerSetUp {

    private static final String LOGIN = "login";
    private static final String BASE_URL = "/users";
    private static final String LOGIN_URL = BASE_URL + "/{login}";

    @MockBean
    private AbstractClient abstractClient;

    @Test
    void shouldGetUser() throws Exception {
        //given
        int followersCount = 2;
        given(abstractClient.getUserDetails(anyString())).willReturn(buildGithubUserResponse(followersCount));

        //when
        MvcResult mvcResult = mockMvc.perform(get(LOGIN_URL, LOGIN)
                        .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andReturn();

        System.out.println(mvcResult.getResponse().getContentAsString());
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
                .createdAt(LocalDateTime.now())
                .build();
    }
}