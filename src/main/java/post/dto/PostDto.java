package post.dto;

public class PostDto {
    private Long postId;
    private String title;
    private String body;

    public PostDto(){}

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
