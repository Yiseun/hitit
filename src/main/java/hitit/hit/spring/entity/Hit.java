package hitit.hit.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import org.hibernate.annotations.ColumnDefault;

import java.util.UUID;

@Entity
public class Hit {

//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    @ColumnDefault("UUID()")
    @Id
    private Long urlId;
    private Long hit;
    private Long totalHit;

    //현재조회수는 현재에 맞는 dailyHit에서 검색

    protected Hit(){}

    public Long getUrlId(){
        return this.urlId;
    }
    public Long getHit(){
        return this.getHit();
    }
    public static Hit addHit(Hit hit){
        return new Hit(hit.urlId,hit.totalHit+1, hit.totalHit+1);
    }
    public static Hit initAllHit(Hit hit){
        return new Hit(hit.urlId,0L, 0L);
    }
    public static Hit initHit(Hit hit){return new Hit(hit.urlId,0L,hit.totalHit);}
    public Hit(Long urlId,Long hit,Long totalHit){
        this.urlId = urlId;
        this.hit = hit;
        this.totalHit = totalHit;
    }
}
