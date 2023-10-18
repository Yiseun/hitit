package hitit.hit;

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
    public void count(final HitRequest hitRequest) throws InterruptedException {
        log.info("arrive HitService");
         HitModerator.assignTask(hitRequest);
        log.info("finish HitService");
    }
}
