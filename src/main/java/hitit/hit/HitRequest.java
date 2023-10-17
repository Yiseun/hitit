package hitit.hit;

public class HitRequest {

    private Long urlId;
    private String url;
    public HitRequest(final String url){
        this.url = url;
    }
    public String getUrl(){
        return this.url;
    }

}
