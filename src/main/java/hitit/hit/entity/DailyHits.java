package hitit.hit.entity;

import hitit.hit.moderator.HitModerator;
import jakarta.persistence.*;
import org.hibernate.annotations.ColumnDefault;

import java.time.LocalDate;
import java.util.UUID;

@Entity
@Table(name = "daily_hits")
public class DailyHits {

    @Id
    @Column(name = "daily_hits_id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    @ColumnDefault("UUID()")
    private UUID dailyHitsId;
    @Column(name = "url_id")
    private Long urlId;
    @Column(name = "date")
    private LocalDate date;
    @Column(name = "date_hit")
    private Long dateHit;

    protected DailyHits(){}

    private DailyHits(Long urlId,LocalDate date,Long dateHit){
        this.urlId = urlId;
        this.date = date;
        this.dateHit = dateHit;
    }

    public static DailyHits of(Long urlId,LocalDate date, Long dateHit){
        return new DailyHits(urlId,date,dateHit);
    }
    public static DailyHits from(Hit hit){
        return new DailyHits(hit.getUrlId(), HitModerator.getServerTime(),hit.getHit());
    }
}
