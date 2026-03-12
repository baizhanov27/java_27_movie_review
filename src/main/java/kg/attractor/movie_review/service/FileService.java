package kg.attractor.movie_review.service;

import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;

public interface FileService {
    void upload(MultipartFile file);

    ResponseEntity<?> download(String filename);
}
