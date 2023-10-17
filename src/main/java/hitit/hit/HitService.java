package hitit.hit;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class HitService {

    private final HitRepository hitRepository;
    private final Logger log = LoggerFactory.getLogger(HitService.class);

    private HitService(final HitRepository hitRepository,final HitDemo hitDemo){
        this.hitRepository = hitRepository;
    }
    public void count(final String url) throws InterruptedException {
        log.info("arrive HitService");
        HitModerator.assignTask(url);
        log.info("finish HitService");
    }
}
