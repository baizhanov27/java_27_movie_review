package kg.attractor.movie_review.service;

import kg.attractor.movie_review.exception.handler.ErrorResponseBody;
import org.springframework.validation.BindingResult;

public interface ErrorService {
    ErrorResponseBody makeResponse(Exception e, String exceptionClass);

    ErrorResponseBody makeResponse(BindingResult bindingResult);
}
