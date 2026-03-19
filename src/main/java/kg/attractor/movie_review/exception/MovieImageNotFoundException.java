package kg.attractor.movie_review.exception;

import java.nio.file.NoSuchFileException;

public class MovieImageNotFoundException extends NoSuchFileException {
    public MovieImageNotFoundException() {
        super("Image not found");
    }
}
