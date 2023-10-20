package hitit.hit.dto.request;

import java.time.LocalDate;

public class PeriodHitRequest {
    private Long urlId;
    private LocalDate startDate;
    private LocalDate endDate;

    public Long getUrlId(){
        return this.urlId;
    }
    public LocalDate getStartDate(){
        return this.startDate;
    }
    public LocalDate getEndDate(){
        return this.endDate;
    }

    private PeriodHitRequest(Long urlId,LocalDate startDate,LocalDate endDate){
        this.urlId = urlId;
        this.startDate = startDate;
        this.endDate = endDate;
    }
//    public static PeriodHitRequest of(Long urlId,PeriodHitRequest periodHitRequest){
//        return new PeriodHitRequest(urlId,periodHitRequest.getStartDate(),periodHitRequest.getEndDate());
//    }
}
