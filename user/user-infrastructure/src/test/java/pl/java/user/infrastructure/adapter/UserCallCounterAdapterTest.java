package pl.java.user.infrastructure.adapter;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import pl.java.user.domain.repository.UserRepository;

import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.BDDMockito.*;

@ExtendWith(MockitoExtension.class)
class UserCallCounterAdapterTest {

    private static final String LOGIN = "login";

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
        userCallCounterAdapter.update(LOGIN);

        //then
        then(userRepository).should().getRequestCount(anyString());
        then(userRepository).should().updateRequestCount(anyString(), anyInt());
    }
}