package hitit.hit.spring;

import hitit.hit.dto.request.PeriodHitRequest;
import hitit.hit.entity.DailyHits;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Repository
public interface DailyHitsRepository  {
    List<DailyHits> findByUrlIdAndPeriod(PeriodHitRequest periodHitRequest);
}
