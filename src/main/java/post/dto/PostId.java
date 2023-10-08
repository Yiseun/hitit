package post.dto;

public class PostId {
    private Long postId;

    protected PostId(){}

    public PostId(Long postId){
        this.postId = postId;
    }
    public Long getPostId(){
        return this.postId;
    }
}
