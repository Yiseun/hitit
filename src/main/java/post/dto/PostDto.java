package post.dto;

public class PostDto {
    private Long postId;
    private String title;
    private String body;

    public PostDto(){}

    public PostDto(Long postId,String title,String body){
        this.postId = postId;
        this.title = title;
        this.body = body;
    }

    public Long getPostId(){
        return this.postId;
    }
    public String getTitle(){
        return this.title;
    }
    public String getBody(){
        return this.body;
    }
}
