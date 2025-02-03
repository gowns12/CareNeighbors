package careneighbors.community.comment;

import java.time.LocalDateTime;
import java.util.List;

public record CommentResponse(
        Long id,
        PatientStatusType patientStatus,
        String additionalContent,
        String authorName,
        LocalDateTime createdAt,
        LocalDateTime updatedAt,
        List<CommentResponse> childComments,
        String imagePath // 이미지 경로 추가
) {
    public static CommentResponse toDto(Comment comment) {
        return new CommentResponse(
                comment.getId(),
                comment.getPatientStatus(),
                comment.getAdditionalContent(),
                comment.getAuthorName(),
                comment.getCreatedAt(),
                comment.getUpdatedAt(),
                comment.getChildComments().stream().map(CommentResponse::toDto).toList(),
                comment.getImagePath() // 이미지 경로 반환
        );
    }
}
