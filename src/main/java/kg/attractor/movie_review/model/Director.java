package kg.attractor.movie_review.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

import java.util.List;

@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
@Entity
@Table(name = "director", schema = "public")
public class Director {
    @Id
    Long id;
    String fullname;

    @OneToMany(mappedBy = "director")
    private List<Movie> movies;
}
