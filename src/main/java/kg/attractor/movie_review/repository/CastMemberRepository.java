package kg.attractor.movie_review.repository;

import kg.attractor.movie_review.model.CastMember;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CastMemberRepository extends JpaRepository<CastMember, Long> {
}
