package kg.attractor.movie_review.service;

import kg.attractor.movie_review.dto.MovieDto;
import kg.attractor.movie_review.exception.NotFoundEntryException;

import java.util.List;

public interface MovieService {
    List<MovieDto> getMovies();

    void createMovie(MovieDto movieDto);

    MovieDto findById(Long id) throws NotFoundEntryException;
}
