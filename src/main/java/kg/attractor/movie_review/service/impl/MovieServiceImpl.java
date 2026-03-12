package kg.attractor.movie_review.service.impl;

import kg.attractor.movie_review.dto.MovieDto;
import kg.attractor.movie_review.service.MovieService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MovieServiceImpl implements MovieService {

    @Override
    public List<MovieDto> getMovies() {
        return List.of(
                MovieDto.builder()
                        .releaseYear(2002)
                        .title("Harry Potter")
                        .director("James Cameron")
                        .build(),
                MovieDto.builder()
                        .releaseYear(1985)
                        .title("Terminator")
                        .director("Mark Goldblatt")
                        .build(),
                MovieDto.builder()
                        .releaseYear(2003)
                        .title("Matrix")
                        .director("Zack Stanberg")
                        .build()
        );
    }

    @Override
    public void createMovie(MovieDto movieDto) {
        System.out.println(movieDto.toString());
    }
}
