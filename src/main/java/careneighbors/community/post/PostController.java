package careneighbors.community.post;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/posts")
public class PostController {

    private final PostService postService;

    @PostMapping
    public PostResponse createPost(@RequestBody PostRequest rq) {
        return postService.create(rq);
    }

    @GetMapping
    public List<PostResponse> readAllPosts() {
        return postService.readAll();
    }

    @GetMapping("/{postId}")
    public PostResponse readPost(@PathVariable Long postId) {
        return postService.read(postId);
    }

    @PutMapping("/{postId}")
    public void updatePost(@PathVariable Long postId, @RequestBody PostRequest rq) {
        postService.update(postId, rq);
    }

    @DeleteMapping("/{postId}")
    public void deletePost(@PathVariable Long postId) {
        postService.delete(postId);
    }
}