package hitit.hit;

import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class HitRequest {

    private Long urlId;
    private LocalDateTime createdDate;
    public HitRequest(final Long urlId){
        this.urlId = urlId;
        this.createdDate = LocalDateTime.now();
    }
    public Long getUrlId(){
        return this.urlId;
    }

    public LocalDateTime getCreatedDate(){
        return createdDate;
//        return createdDate.format(DateTimeFormatter.ofPattern("yyyy-mm-dd"));
    }

}
