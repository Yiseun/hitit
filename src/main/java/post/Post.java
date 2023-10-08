package post;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class Post {

    @Id
    private Long postId;
    private String title;
    private String body;

    protected Post(){}
    private Post(Long postId,String title,String body){
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

    public static Post of(Long postId,String title,String body){
        return new Post(postId,title,body);
    }

}
