package kg.attractor.movie_review.service.impl;

import kg.attractor.movie_review.exception.handler.ErrorResponseBody;
import kg.attractor.movie_review.service.ErrorService;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class ErrorServiceImpl implements ErrorService {

    @Override
    public ErrorResponseBody makeResponse(Exception e, String exceptionClass) {
        String msg = e.getMessage();
        return ErrorResponseBody.builder()
                .title(exceptionClass)
                .reasons(Map.of("errors", List.of(msg)))
                .build();
    }

    @Override
    public ErrorResponseBody makeResponse(BindingResult bindingResult) {
        Map<String, List<String>> reasons = new HashMap<>();

        bindingResult.getFieldErrors().stream()
                .filter(e -> e.getDefaultMessage() != null)
                .forEach(e -> {
                    List<String> errors = new ArrayList<>();
                    errors.add(e.getDefaultMessage());
                    if (!reasons.containsKey(e.getField())) {
                        reasons.put(e.getField(), errors);
                    }
                });
        return ErrorResponseBody.builder()
                .title("Validation errors")
                .reasons(reasons)
                .build();
    }
}
