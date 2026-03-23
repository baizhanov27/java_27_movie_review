package kg.attractor.movie_review.exception;

public class MovieImageNotFoundException extends NotFoundEntryException {
    public MovieImageNotFoundException() {
        super("Image not found");
    }
}
