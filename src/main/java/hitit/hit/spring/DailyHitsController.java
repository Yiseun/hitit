package hitit.hit.spring;

import hitit.hit.dto.request.PeriodHitRequest;
import hitit.hit.dto.response.PeriodHitResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/date")
@RestController
public class DailyHitsController {

    private final DailyHitsService dailyHitsService;
    private final Logger log = LoggerFactory.getLogger(DailyHitsController.class);

    private DailyHitsController(final DailyHitsService dailyHitsService){
        this.dailyHitsService = dailyHitsService;
    }
    @PostMapping
    private ResponseEntity<List<PeriodHitResponse>> periodHitRead(@RequestBody PeriodHitRequest periodHitRequest){
        List<PeriodHitResponse> periodHitResponses = dailyHitsService.periodHitRead(periodHitRequest);
        return ResponseEntity.ok(periodHitResponses);
    }
}
