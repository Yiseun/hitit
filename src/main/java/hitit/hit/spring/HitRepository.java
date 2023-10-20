package hitit.hit.spring;

import hitit.hit.dto.response.HitResponse;
import hitit.hit.entity.Hit;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface HitRepository extends JpaRepository<Hit, Long> {

    Optional<Hit> findById(Long urlId);
}
