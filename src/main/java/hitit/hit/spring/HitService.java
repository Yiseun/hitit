package hitit.hit.spring;

import hitit.hit.dto.request.HitRequest;
import hitit.hit.dto.response.HitResponse;
import hitit.hit.dto.response.TotalHitResponse;
import hitit.hit.moderator.HitModerator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class HitService {

    private final HitRepository hitRepository;
    private final Logger log = LoggerFactory.getLogger(HitService.class);

    private HitService(final HitRepository hitRepository){
        this.hitRepository = hitRepository;
    }

    public HitResponse dailyAndTotalHitRead(final HitRequest HitRequest){
        return HitResponse.from((hitRepository.findById(HitRequest.getUrlId()).orElseThrow()));
    }
    public void hitIncrease(final HitRequest hitRequest) throws InterruptedException {
         HitModerator.assignTask(hitRequest);
    }

}
