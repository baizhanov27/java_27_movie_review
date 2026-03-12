package kg.attractor.movie_review.controller;

import kg.attractor.movie_review.service.FileService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("images")
@RequiredArgsConstructor
public class ImageController {

    private final FileService fileService;

    @PostMapping
    public HttpStatus upload(MultipartFile file) {
        fileService.upload(file);
        return HttpStatus.OK;
    }

    @GetMapping("{filename}")
    public ResponseEntity<?> download(@PathVariable String filename) {
        return fileService.download(filename);
    }
}
