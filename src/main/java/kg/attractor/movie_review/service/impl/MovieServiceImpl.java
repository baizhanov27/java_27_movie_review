package kg.attractor.movie_review.service.impl;

import kg.attractor.movie_review.dao.MovieDao;
import kg.attractor.movie_review.dao.MovieImageDao;
import kg.attractor.movie_review.dto.DirectorDto;
import kg.attractor.movie_review.dto.MovieDto;
import kg.attractor.movie_review.exception.MovieNotFoundException;
import kg.attractor.movie_review.exception.NotFoundEntryException;
import kg.attractor.movie_review.model.Movie;
import kg.attractor.movie_review.model.MovieImage;
import kg.attractor.movie_review.service.DirectorService;
import kg.attractor.movie_review.service.MovieService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class MovieServiceImpl implements MovieService {
    private final MovieDao movieDao;
    private final DirectorService directorService;
    private final MovieImageDao movieImageDao;

    @Override
    public List<MovieDto> getMovies() {
        List<Movie> list = movieDao.findAll();
        List<MovieDto> movies = new ArrayList<>();

        list.forEach(e -> {
            MovieDto movieDto = MovieDto.builder()
                    .id(e.getId())
                    .title(e.getName())
                    .releaseYear(e.getReleaseYear())
                    .description(e.getDescription())
                    .build();

            DirectorDto directorDto = directorService.findById(e.getDirectorId());

            movieDto.setDirector(directorDto.fullname());

            movies.add(movieDto);
        });

        return movies;
    }

    @Override
    public void createMovie(MovieDto movieDto) {
        System.out.println(movieDto.toString());
    }

    @Override
    public MovieDto findById(Long id) throws NotFoundEntryException {
        Movie movie = movieDao.findById(id)
                .orElseThrow(MovieNotFoundException::new);
        DirectorDto director = directorService.findById(movie.getDirectorId());
        Optional<MovieImage> image = movieImageDao.findByMovieId(id);

        MovieDto result = MovieDto.builder()
                .title(movie.getName())
                .releaseYear(movie.getReleaseYear())
                .description(movie.getDescription())
                .director(director.fullname())
                .build();

        image.ifPresent(movieImage -> result.setImageName(movieImage.getFilename()));

        return result;
    }
}
