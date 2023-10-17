package hitit.hit;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HitRepository extends JpaRepository<Hit, Long> {
}
