package kg.attractor.movie_review.controller.api;

import kg.attractor.movie_review.dto.MovieImageDto;
import kg.attractor.movie_review.exception.MovieImageNotFoundException;
import kg.attractor.movie_review.service.FileService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/images")
@RequiredArgsConstructor
public class ApiImageController {

    private final FileService fileService;

    @PostMapping
    public String upload(MovieImageDto dto) {
        fileService.upload(dto);
        return "redirect:/";
    }

    @GetMapping("{movieId}")
    public ResponseEntity<?> download(@PathVariable Long movieId) throws MovieImageNotFoundException {
        return fileService.download(movieId);
    }
}
