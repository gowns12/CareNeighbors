package careneighbors.community.Image;

import careneighbors.community.exception.PostNotFoundException;
import careneighbors.community.post.Post;
import careneighbors.community.post.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ImageService {

    private final ImageRepository imageRepository;
    private final PostRepository postRepository;

    public ImageResponse upload(ImageRequest rq) {
        Post post = postRepository.findById(rq.postId())
                .orElseThrow(() -> new PostNotFoundException("Post not found with id: " + rq.postId()));
        Image image = new Image(rq.url(), post);
        imageRepository.save(image);
        return ImageResponse.toDto(image);
    }

    public void delete(Long imageId) {
        imageRepository.deleteById(imageId);
    }
}