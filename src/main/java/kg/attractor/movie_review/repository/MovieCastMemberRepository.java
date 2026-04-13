package kg.attractor.movie_review.repository;

import kg.attractor.movie_review.model.MovieCastMember;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MovieCastMemberRepository extends JpaRepository<MovieCastMember, MovieCastMember.MovieCastMemberId> {
}
