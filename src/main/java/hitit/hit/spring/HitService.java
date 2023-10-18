package hitit.hit.spring;

import hitit.hit.dto.request.HitRequest;
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
    public void hitIncrease(final HitRequest hitRequest) throws InterruptedException {
         HitModerator.assignTask(hitRequest);
    }
}
