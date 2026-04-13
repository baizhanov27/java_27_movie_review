package kg.attractor.movie_review.exception.handler;

import jakarta.servlet.http.HttpServletRequest;
import kg.attractor.movie_review.exception.NotFoundEntryException;
import kg.attractor.movie_review.service.ErrorService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.sql.SQLException;

@ControllerAdvice
@RequiredArgsConstructor
public class GlobalControllerAdvice {
    private final ErrorService errorService;

    @ExceptionHandler(NotFoundEntryException.class)
    private String noSuchFileExceptionHandler(HttpServletRequest request, Model model, NotFoundEntryException e) {
        model.addAttribute("status", HttpStatus.NOT_FOUND.value());
        model.addAttribute("reason", HttpStatus.NOT_FOUND.getReasonPhrase() + ": " + e.getMessage());
        model.addAttribute("details", request);
        return "errors/error";
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
