package careneighbors.community.post;

import careneighbors.community.exception.PostNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PostService {

    private final PostRepository postRepository;

    public PostResponse create(PostRequest rq) {
        Post post = new Post(rq.title(), rq.content(), rq.authorName());
        postRepository.save(post);
        return PostResponse.toDto(post);
    }

    public List<PostResponse> readAll() {
        return postRepository.findAll().stream()
                .map(PostResponse::toDto)
                .toList();
    }

    public PostResponse read(Long postId) {
        Post post = postRepository.findById(postId)
                .orElseThrow(() -> new PostNotFoundException("Post not found with id: " + postId));
        return PostResponse.toDto(post);
    }

    @Transactional
    public void update(Long postId, PostRequest rq) {
        Post post = postRepository.findById(postId)
                .orElseThrow(() -> new PostNotFoundException("Post not found with id: " + postId));
        post.update(rq.title(), rq.content());
    }

    public void delete(Long postId) {
        postRepository.deleteById(postId);
    }
}