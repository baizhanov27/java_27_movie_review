package kg.attractor.movie_review.exception.handler;

import kg.attractor.movie_review.exception.NotFoundEntryException;
import kg.attractor.movie_review.service.ErrorService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.sql.SQLException;

@RestControllerAdvice
@RequiredArgsConstructor
public class GlobalControllerAdvice {
    private final ErrorService errorService;

    @ExceptionHandler(NotFoundEntryException.class)
    private ResponseEntity<ErrorResponseBody> noSuchFileExceptionHandler(NotFoundEntryException e) {
        return new ResponseEntity<>(errorService.makeResponse(e, e.getClass().getSimpleName()), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(SQLException.class)
    private ResponseEntity<ErrorResponseBody> sqlExceptionHandler(SQLException e) {
        return new ResponseEntity<>(errorService.makeResponse(e, e.getClass().getSimpleName()), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    private ResponseEntity<ErrorResponseBody> validationHandler(MethodArgumentNotValidException e) {
        return new ResponseEntity<>(errorService.makeResponse(e.getBindingResult()), HttpStatus.BAD_REQUEST);
    }
}
