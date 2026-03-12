package kg.attractor.movie_review.service;

import kg.attractor.movie_review.dto.MovieDto;

import java.util.List;

public interface MovieService {
    List<MovieDto> getMovies();

    void createMovie(MovieDto movieDto);
}
