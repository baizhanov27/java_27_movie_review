package kg.attractor.movie_review.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MovieImage {
    private Long id;
    private Long movieId;
    private String filename;
}
