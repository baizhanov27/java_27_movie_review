package kg.attractor.movie_review.dao;

import kg.attractor.movie_review.model.MovieImage;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.support.DataAccessUtils;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@RequiredArgsConstructor
public class MovieImageDao {
    private final JdbcTemplate jdbcTemplate;

    public void save(Long movieId, String filename) {
        String sql = "insert into movie_images (movie_id, filename) " +
                "values((select id from movie where id = ?), ?)";
        jdbcTemplate.update(sql, movieId, filename);
    }

    public Optional<MovieImage> findByMovieId(Long movieId) {
        String sql = "select * from movie_images where movie_id = ?";
        return Optional.ofNullable(
                DataAccessUtils.singleResult(
                        jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(MovieImage.class), movieId)
                )
        );
    }
}
