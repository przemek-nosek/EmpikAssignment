package pl.java.shared.validation;

import io.vavr.control.Either;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

import java.util.function.Consumer;

@ToString
@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
public class ValidationResult<Failure, Success> {

    private final Either<Failure, Success> result;

    public boolean isSuccess() {
        return this.result.isRight();
    }

    public boolean isFailure() {
        return this.result.isLeft();
    }

    public Success getSuccess() {
        return this.result.get();
    }

    public Failure getFailure() {
        return this.result.getLeft();
    }

    public static <Failure, Success> ValidationResult<Failure, Success> success() {
        return new ValidationResult<>(Either.right(null));
    }

    public static <Failure, Success> ValidationResult<Failure, Success> failure(Failure failure) {
        return new ValidationResult<>(Either.left(failure));
    }

    public ValidationResult<Failure, Success> onFailure(Consumer<Failure> handler) {
        if (this.result.isLeft()) {
            handler.accept(this.result.getLeft());
        }
        return this;
    }

    public ValidationResult<Failure, Success> onSuccess(Consumer<Success> handler) {
        if (this.result.isRight()) {
            handler.accept(this.result.get());
        }
        return this;
    }
}
