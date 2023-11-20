package pl.java.shared.validation;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public enum CommonErrorMessages implements ErrorType {

    INVALID_PARAMETER_VALUE("Parameter: %s can not have value: %s");

    private final String message;
}
