package kg.attractor.movie_review.service.impl;

import kg.attractor.movie_review.dto.DirectorDto;
import kg.attractor.movie_review.exception.DirectorNotFoundException;
import kg.attractor.movie_review.model.Director;
import kg.attractor.movie_review.repository.DirectorRepository;
import kg.attractor.movie_review.service.DirectorService;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class DirectorServiceImpl implements DirectorService {
    private final DirectorRepository directorRepository;

    @SneakyThrows
    @Override
    public DirectorDto findById(long id) {
        Director director = directorRepository.findById(id)
                .orElseThrow(DirectorNotFoundException::new);

        return new DirectorDto()
                .id(director.getId())
                .fullname(director.getFullname());
    }
}
