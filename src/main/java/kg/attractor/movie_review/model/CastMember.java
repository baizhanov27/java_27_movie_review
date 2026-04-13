package kg.attractor.movie_review.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "cast_member")
public class CastMember {
    @Id
    private Long id;
    private String fullname;

    @OneToMany
    @JoinColumn(name = "cast_member_id")
    private List<MovieCastMember> movieCastMembers;
}
