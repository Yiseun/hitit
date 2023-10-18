package hitit.hit;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/hit")
@RestController
public class HitController {
    private final HitService hitService;

    private final Logger log = LoggerFactory.getLogger(HitService.class);

    private HitController(final HitService hitService){
        this.hitService = hitService;
    }

    @GetMapping("/")
    public ResponseEntity<Void> inC(@RequestParam Long url) throws InterruptedException {
        log.info("arrive HitController");
        HitRequest hitRequest = new HitRequest(url);
        System.out.println(hitRequest.getCreatedDate());
        hitService.count(hitRequest);
        log.info("finish HitController");
        return ResponseEntity.noContent().build();
    }
}
