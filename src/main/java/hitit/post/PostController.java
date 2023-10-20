package hitit.post;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import hitit.post.dto.PostDto;
import hitit.post.dto.PostId;

@RestController
public class PostController {

    private final PostService postService;

    private PostController(final PostService postService){
        this.postService = postService;
    }

    @PostMapping
    public ResponseEntity<PostId> creatPost(@RequestBody PostDto postDto){
        PostId postId = postService.createPost(postDto);
        return ResponseEntity.ok(postId);
    }

    @GetMapping("/{postId}")
    public ResponseEntity<PostDto> readPost(@PathVariable PostId postId){
        PostDto postDto = postService.readPost(postId);
        return ResponseEntity.ok(postDto);
    }
}
