package pl.java.shared.validation;

import lombok.experimental.UtilityClass;

import java.util.function.Supplier;

import static java.util.Objects.isNull;
import static pl.java.shared.validation.CommonErrorMessages.INVALID_PARAMETER_VALUE;

@UtilityClass
public class DataValidator {

    public static Supplier<ValidationResult<Error, Void>> validateIsNotNull(String name, Object value) {
        return () -> isNull(value)
                ? ValidationResult.failure(
                Error.format(INVALID_PARAMETER_VALUE.getType(), INVALID_PARAMETER_VALUE.getMessage(), name, null))
                : ValidationResult.success();
    }

    @SafeVarargs
    public static ValidationResult<Error, Void> combine(Supplier<ValidationResult<Error, Void>>... suppliers) {
        for (Supplier<ValidationResult<Error, Void>> supplier : suppliers) {
            ValidationResult<Error, Void> errorVoidValidationResult = supplier.get();
            if (errorVoidValidationResult.isFailure()) {
                return errorVoidValidationResult;
            }
        }
        return ValidationResult.success();
    }
}
