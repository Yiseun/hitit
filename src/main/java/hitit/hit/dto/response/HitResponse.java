package hitit.hit.dto.response;

import hitit.hit.entity.Hit;

public class HitResponse {
    private Long urlId;
    private Long hit;
    private Long totalHit;

    private HitResponse(Long urlId,Long hit, Long totalHit){
        this.urlId = urlId;
        this.hit = hit;
        this.totalHit = totalHit;
    }

    public Long getUrlId(){
        return this.urlId;
    }
    public Long getHit(){
        return this.hit;
    }
    public Long getTotalHit(){
        return this.totalHit;
    }
    public static HitResponse from(Hit hit){
        return new HitResponse(hit.getUrlId(),hit.getHit(),hit.getTotalHit());
    }
}
