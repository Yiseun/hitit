package post;

import org.springframework.stereotype.Service;
import post.dto.PostDto;
import post.dto.PostId;

@Service
public class PostService {
    private final PostRepository postRepository;

    private PostService(final PostRepository postRepository){
        this.postRepository = postRepository;
    }

    public PostId createPost(PostDto postDto){
        Post post = Post.of(postDto.getPostId(), postDto.getTitle(), postDto.getBody());
        postRepository.save(post);
        return new PostId(post.getPostId());
    }

    public PostDto readPost(PostId postId){
        Post post = postRepository.findByPostId(postId.getPostId());
        return Post.entityToDto(post);
    }
}
