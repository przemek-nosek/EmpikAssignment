package pl.java.user.domain.exception.messages;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import pl.java.shared.validation.ErrorType;

@RequiredArgsConstructor
@Getter
public enum DomainErrorMessages implements ErrorType {
    USER_FOLLOWERS_COUNT_IS_ZERO_CANT_DIVIDE("User's %s follower count is zero therefore can not divide."),
    USER_RETURNED_BY_API_IS_NULL("User %s returned by API was null, can not process.");

    private final String message;
}
