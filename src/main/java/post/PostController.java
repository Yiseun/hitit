package post;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import post.dto.PostDto;
import post.dto.PostId;

@RestController
public class PostController {

    private final PostService postService;

    private PostController(final PostService postService){
        this.postService = postService;
    }

    public ResponseEntity<PostId> creatPos(@RequestBody PostDto postDto){
        PostId postId = postService.createPost(postDto);
        return ResponseEntity.ok(postId);
    }
}
