package hitit.hit.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class Hit {

    @Id
    @Column(name = "url_Id")
    private Long urlId;
    @Column(name = "hit")
    private Long hit;
    @Column(name = "total_hit")
    private Long totalHit;


    protected Hit(){}

    public Long getUrlId(){
        return this.urlId;
    }
    public Long getHit(){
        return this.hit;
    }

    public Long getTotalHit(){return this.getTotalHit();}
    public Hit(Long urlId,Long hit,Long totalHit){
        this.urlId = urlId;
        this.hit = hit;
        this.totalHit = totalHit;
    }
    public static Hit addHit(Hit hit){
        return new Hit(hit.urlId,hit.totalHit+1, hit.totalHit+1);
    }
    public static Hit initAllHit(Long urlId){
        return new Hit(urlId,0L, 0L);
    }
    public static Hit initHit(Hit hit){return new Hit(hit.urlId,0L,hit.totalHit);}



}
