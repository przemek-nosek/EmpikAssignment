package pl.java.user.application.in.rest;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.java.user.application.in.response.UserResponse;
import pl.java.user.application.in.rest.specification.UserSpecification;
import pl.java.user.application.out.mapper.UserOutputMapper;
import pl.java.user.domain.port.in.GetUserUseCase;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
class UserController implements UserSpecification {

    private final GetUserUseCase getUserUseCase;
    private final UserOutputMapper userOutputMapper;

    @Override
    @GetMapping("/{login}")
    public ResponseEntity<UserResponse> getUser(@PathVariable String login) {
        UserResponse userResponse = userOutputMapper.toUserResponse(getUserUseCase.getUser(login));
        return ResponseEntity.ok(userResponse);
    }
}
