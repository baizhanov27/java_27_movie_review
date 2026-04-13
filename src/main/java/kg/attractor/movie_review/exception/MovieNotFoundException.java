package kg.attractor.movie_review.exception;

public class MovieNotFoundException extends NotFoundEntryException {
    public MovieNotFoundException() {
        super("Movie not found");
    }
}
