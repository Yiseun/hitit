package hitit.hit.spring;

import hitit.hit.dto.request.HitRequest;
import hitit.hit.dto.request.TotalHitRequest;
import hitit.hit.dto.response.TotalHitResponse;
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

    @GetMapping("/total/{url}")
    public ResponseEntity<TotalHitResponse> totalHitRead(@RequestParam Long totalurl){
        TotalHitRequest totalHitRequest = new TotalHitRequest(totalurl);
        TotalHitResponse totalHitResponse = hitService.totalHitResponse(totalHitRequest);
        return ResponseEntity.ok(totalHitResponse);
    }
    @GetMapping("/{url}")
    public ResponseEntity<Void> hitIncrease(@RequestParam Long url) throws InterruptedException {
        HitRequest hitRequest = new HitRequest(url);
        hitService.hitIncrease(hitRequest);
        return ResponseEntity.noContent().build();
    }
}
