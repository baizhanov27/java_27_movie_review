package kg.attractor.movie_review.service;

import kg.attractor.movie_review.dto.MovieImageDto;
import kg.attractor.movie_review.exception.MovieImageNotFoundException;
import org.springframework.http.ResponseEntity;

public interface FileService {

    void upload(MovieImageDto movieImageDto);

    ResponseEntity<?> download(Long movieId) throws MovieImageNotFoundException;
}
