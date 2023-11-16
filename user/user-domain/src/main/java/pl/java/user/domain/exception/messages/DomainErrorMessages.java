package pl.java.user.domain.exception.messages;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public enum DomainErrorMessages {
    USER_FOLLOWERS_COUNT_IS_ZERO_CANT_DIVIDE("User's %s follower count is zero therefore can not divide.");

    private final String message;
}
