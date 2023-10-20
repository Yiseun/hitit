package hitit.hit.spring;

import hitit.hit.dto.request.PeriodHitRequest;
import hitit.hit.dto.response.PeriodHitResponse;
import hitit.hit.entity.DailyHits;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DailyHitsService {

    private final DailyHitsRepository dailyHitsRepository;
    private final Logger log = LoggerFactory.getLogger(DailyHitsService.class);

    private DailyHitsService(final DailyHitsRepository dailyHitsRepository){
        this.dailyHitsRepository = dailyHitsRepository;
    }
    public List<PeriodHitResponse> periodHitRead(final PeriodHitRequest periodHitRequest){
        List<DailyHits> dailyHits = dailyHitsRepository.findByUrlIdAndPeriod(periodHitRequest);
        return PeriodHitResponse.from(dailyHits);
    }
}
