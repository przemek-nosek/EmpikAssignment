package pl.java.user.domain.exception;

public class ZeroFollowersException extends RuntimeException {

    public ZeroFollowersException(String message) {
        super(message);
    }
}
