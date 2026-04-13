package kg.attractor.movie_review.controller;

import kg.attractor.movie_review.exception.NotFoundEntryException;
import kg.attractor.movie_review.service.MovieService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("movies")
@RequiredArgsConstructor
public class MovieController {
    private final MovieService movieService;

    @GetMapping
    public String getMovieList(Model model) {
        model.addAttribute("movies", movieService.getMovies());
        return "movies/movies";
    }

    @GetMapping("{id}")
    public String findById(Model model, @PathVariable Long id) throws NotFoundEntryException {
        model.addAttribute("movie", movieService.findById(id));
        return "movies/movie";
    }
}
