package kg.attractor.movie_review.exception;

import java.nio.file.NoSuchFileException;

public class UserNotFoundException extends NoSuchFileException {
    public UserNotFoundException() {
        super("User not found");
    }
}
