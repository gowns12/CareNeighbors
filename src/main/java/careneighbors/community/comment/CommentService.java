package careneighbors.community.comment;

import careneighbors.community.exception.CommentNotFoundException;
import careneighbors.community.exception.PostNotFoundException;
import careneighbors.community.post.Post;
import careneighbors.community.post.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class CommentService {

    private final CommentRepository commentRepository;
    private final PostRepository postRepository;

    public CommentResponse create(CommentRequest rq) {
        Post post = postRepository.findById(rq.postId())
                .orElseThrow(() -> new PostNotFoundException("Post not found with id: " + rq.postId()));
        Comment guardianComment = rq.guardianCommentId() != null ?
                commentRepository.findById(rq.guardianCommentId())
                        .orElseThrow(() -> new CommentNotFoundException("Parent comment not found with id: " + rq.guardianCommentId())) :
                null;
        Comment comment = new Comment(rq.patientStatus(), rq.additionalContent(), rq.authorName(), post, guardianComment);
        commentRepository.save(comment);
        return CommentResponse.toDto(comment);
    }

    public CommentResponse read(Long commentId) {
        Comment comment = commentRepository.findById(commentId)
                .orElseThrow(() -> new CommentNotFoundException("Comment not found with id: " + commentId));
        return CommentResponse.toDto(comment);
    }

    @Transactional
    public void update(Long commentId, CommentRequest rq) {
        Comment comment = commentRepository.findById(commentId)
                .orElseThrow(() -> new CommentNotFoundException("Comment not found with id: " + commentId));
        comment.update(rq.patientStatus(), rq.additionalContent());
    }

    public void delete(Long commentId) {
        commentRepository.deleteById(commentId);
    }
}