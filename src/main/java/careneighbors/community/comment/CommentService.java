package careneighbors.community.comment;

import careneighbors.community.exception.CommentNotFoundException;
import careneighbors.community.exception.PostNotFoundException;
import careneighbors.community.post.Post;
import careneighbors.community.post.PostRepository;
import careneighbors.patient.Patient;
import careneighbors.patient.PatientMisMatchException;
import careneighbors.patient.PatientRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class CommentService {

    private final CommentRepository commentRepository;
    private final PostRepository postRepository;
    private final PatientRepository patientRepository;

    public CommentResponse create(CommentRequest rq) {
        Post post = postRepository.findById(rq.postId())
                .orElseThrow(() -> new PostNotFoundException("Post not found with id: " + rq.postId()));
        Comment guardianComment = rq.guardianCommentId() != null ?
                commentRepository.findById(rq.guardianCommentId())
                        .orElseThrow(() -> new CommentNotFoundException("Parent comment not found with id: " + rq.guardianCommentId())) :
                null;
        Patient patient = patientRepository.findById(rq.patientId())
                .orElseThrow(() -> new PatientMisMatchException("Patient not found with id: " + rq.patientId()));

        // 환자와 게시물의 연관성 검증
        if (!post.isRelatedToPatient(patient)) {
            throw new PatientMisMatchException("Patient does not match the post");
        }

        Comment comment = new Comment(rq.patientStatus(), rq.additionalContent(), rq.authorName(), post, guardianComment, patient);
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

        Patient patient = comment.getPatient();
        if (rq.patientId() != null && !comment.getPatient().getId().equals(rq.patientId())) {
            patient = patientRepository.findById(rq.patientId())
                    .orElseThrow(() -> new PatientMisMatchException("Patient not found with id: " + rq.patientId()));
            if (!comment.getPost().isRelatedToPatient(patient)) {
                throw new PatientMisMatchException("New patient does not match the post");
            }
        }

        comment.update(rq.patientStatus(), rq.additionalContent(), patient);
    }

    public void delete(Long commentId) {
        commentRepository.deleteById(commentId);
    }
}