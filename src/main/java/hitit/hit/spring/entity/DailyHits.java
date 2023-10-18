package hitit.hit.spring.entity;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.UUID;

@Entity
public class DailyHits {

    @Id
    @Column(name = "daily_hits_id")
    private UUID dailyHitsId;
    @Column(name = "url_id")
    private Long urlId;
    @Column(name = "date")
    private LocalDate date;
    @Column(name = "date_hit")
    private Long dateHit;

    protected DailyHits(){}

    private DailyHits(Long urlId,LocalDate date,Long dateHit){
        this.dailyHitsId = UUID.randomUUID();
        this.urlId = urlId;
        this.date = date;
        this.dateHit = dateHit;
    }

    public static DailyHits of(Long urlId,LocalDate date, Long dateHit){
        return new DailyHits(urlId,date,dateHit);
    }
}
