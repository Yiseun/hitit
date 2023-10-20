package hitit.hit.dto.response;

import hitit.hit.entity.DailyHits;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class PeriodHitResponse {
    private Long urlId;
    private LocalDate date;
    private Long dateHit;

    private PeriodHitResponse(Long urlId,LocalDate date,Long dateHit){
        this.urlId = urlId;
        this.date = date;
        this.dateHit = dateHit;
    }

    public Long getUrlId(){
        return this.urlId;
    }
    public LocalDate getDate(){
        return this.date;
    }
    public Long getDateHit(){
        return this.dateHit;
    }

    public static PeriodHitResponse from(DailyHits dailyHits){
        return new PeriodHitResponse(dailyHits.getUrlId(),dailyHits.getDate(), dailyHits.getDateHit());
    }
    public static List<PeriodHitResponse> from(List<DailyHits> dailyHits){
        List<PeriodHitResponse> results = new ArrayList<>();
        for (DailyHits dailyHit : dailyHits) {
            results.add(PeriodHitResponse.from(dailyHit));
        }
        return results;
    }
}
