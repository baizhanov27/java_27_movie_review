package kg.attractor.movie_review.service.impl;

import kg.attractor.movie_review.service.FileService;
import lombok.SneakyThrows;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.UUID;

@Service
public class FileServiceImpl implements FileService {
    private static final String UPLOAD_DIR = "data/";

    @SneakyThrows
    private String saveUploadedFile(MultipartFile file, String subdir) {
        String uuid = UUID.randomUUID().toString();
        String resultFilename = uuid + "_" + file.getOriginalFilename();

        Path pathDir = Paths.get(UPLOAD_DIR + subdir); // data/images
        if (!Files.exists(pathDir)) Files.createDirectory(pathDir);

        Path filePath = Paths.get(pathDir + "/" + resultFilename);
        if (!Files.exists(filePath)) {
            Files.createFile(filePath);
        }

        try (OutputStream os = Files.newOutputStream(filePath)) {
            os.write(file.getBytes());
        } catch (IOException e) {
            e.printStackTrace();
        }

        return resultFilename;
    }

    private byte[] getDownloadedFile(String filename, String subdir) throws IOException {
        return Files.readAllBytes(Paths.get(UPLOAD_DIR + subdir + "/" + filename));
    }

    @Override
    public void upload(MultipartFile file) {
        String resultFilename = saveUploadedFile(file, "images");
        System.out.println(resultFilename);
    }

    @Override
    public ResponseEntity<?> download(String filename) {
        try {
            Resource resource = new ByteArrayResource(getDownloadedFile(filename, "images"));

            return ResponseEntity.ok()
                    .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + filename + "\"")
                    .contentLength(resource.contentLength())
                    .contentType(MediaType.IMAGE_JPEG)
                    .body(resource);
        } catch (IOException e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body("Image not found");
        }
    }
}
