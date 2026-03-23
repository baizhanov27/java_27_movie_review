package kg.attractor.movie_review.dao;

import kg.attractor.movie_review.dao.mapper.UserMapper;
import kg.attractor.movie_review.model.User;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.support.DataAccessUtils;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Component;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class UserDao {
    private final JdbcTemplate jdbcTemplate;
    private final NamedParameterJdbcTemplate namedParameterJdbcTemplate;
    private final KeyHolder keyHolder = new GeneratedKeyHolder();

    public List<User> getAllUsers() {
        String sql = "select * from usr;";

        return jdbcTemplate.query(sql, new UserMapper());
    }

    public Optional<User> findById(int id) {
        String sql = "select * from usr where id = ?";

        return Optional.ofNullable(
                DataAccessUtils.singleResult(
                        jdbcTemplate.query(sql, new UserMapper(), id)
                )
        );
    }

    public Optional<User> findByEmailNamed(String email) {
        String sql = "select * from usr where email = :email";

        return Optional.ofNullable(
                DataAccessUtils.singleResult(
                        namedParameterJdbcTemplate.query(
                                sql,
                                new MapSqlParameterSource()
                                        .addValue("email", email),
                                new UserMapper()
                        )
                )
        );
    }

    public void create(User user) throws SQLException {
        String sql = "insert into usr(email, username, password) " +
                "values(?,?,?)";

        jdbcTemplate.update(sql, user.getEmail(), user.getUsername(), user.getPassword());
    }
}
