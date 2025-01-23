package careneighbors.community.comment;

import java.time.LocalDateTime;
import java.util.List;

public record CommentResponse(
        Long id,
        String content,
        String authorName,
        LocalDateTime createdAt,
        LocalDateTime updatedAt,
        List<CommentResponse> childComments
) {
    public static CommentResponse toDto(Comment comment) {
        return new CommentResponse(
                comment.getId(),
                comment.getContent(),
                comment.getAuthorName(),
                comment.getCreatedAt(),
                comment.getUpdatedAt(),
                comment.getChildComments().stream().map(CommentResponse::toDto).toList()
        );
    }
}