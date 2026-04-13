package kg.attractor.movie_review.model;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "movie")
public class Movie {
    @Id
    private Long id;
    private String name;
    private Integer releaseYear;
    private String description;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "director_id")
    private Director director;

    @OneToMany(fetch = FetchType.EAGER)
    @JoinColumn(name = "movie_id")
    private List<MovieCastMember> movieCastMembers;
}
