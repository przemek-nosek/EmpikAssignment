package pl.java.user.infrastructure.adapter;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import pl.java.user.domain.model.User;
import pl.java.user.domain.repository.UserRepository;

import java.time.LocalDateTime;

import static org.mockito.ArgumentMatchers.*;
import static org.mockito.BDDMockito.*;

@ExtendWith(MockitoExtension.class)
class UserCallCounterAdapterTest {

    @Mock
    private UserRepository userRepository;
    @InjectMocks
    private UserCallCounterAdapter userCallCounterAdapter;

    @Test
    void update_shouldInvokeRepositoryCallsTwoTimes() {
        //given
        given(userRepository.getRequestCount(anyString())).willReturn(5);
        willDoNothing().given(userRepository).updateRequestCount(anyString(), anyInt());

        //when
         userCallCounterAdapter.update(buildUser());

         //then
        then(userRepository).should().getRequestCount(anyString());
        then(userRepository).should().updateRequestCount(anyString(), anyInt());
    }

    private User buildUser() {
        return User.builder()
                .id(123)
                .name("name")
                .login("login")
                .avatarUrl("avatarUrl")
                .calculations(15)
                .createdAt(LocalDateTime.now())
                .type("type")
                .build();
    }
}