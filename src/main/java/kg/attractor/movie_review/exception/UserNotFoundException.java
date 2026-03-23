package kg.attractor.movie_review.exception;

public class UserNotFoundException extends NotFoundEntryException {
    public UserNotFoundException() {
        super("User not found");
    }
}
