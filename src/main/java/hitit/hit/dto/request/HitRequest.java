package hitit.hit.dto.request;

import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class HitRequest {

    private Long urlId;
    public HitRequest(final Long urlId){
        this.urlId = urlId;
    }
    public Long getUrlId(){
        return this.urlId;
    }

}
