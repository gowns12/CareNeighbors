package careneighbors.community.comment;

public record CommentRequest(
        String content,
        String authorName,
        Long postId,
        Long guardianCommentId
) {
}