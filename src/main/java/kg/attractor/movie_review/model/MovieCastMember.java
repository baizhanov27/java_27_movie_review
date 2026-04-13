package kg.attractor.movie_review.model;

import jakarta.persistence.Embeddable;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
@Entity
@Table(name = "movie_cast_member")
public class MovieCastMember {
    @EmbeddedId
    private MovieCastMemberId movieCastMemberId;
    private String role;

    @Embeddable
    @Getter
    @Setter
    public static class MovieCastMemberId implements Serializable {
        @ManyToOne
        @JoinColumn(name = "movie_id")
        private Movie movie;

        @ManyToOne
        @JoinColumn(name = "cast_member_id")
        private CastMember castMember;
    }
}
