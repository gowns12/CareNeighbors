package careneighbors.community.comment;

public record CommentRequest(
        PatientStatusType patientStatus,
        String additionalContent,
        String authorName,
        Long postId,
        Long guardianCommentId,
        Long patientId,
        String imagePath // 이미지 경로 추가
) {
}
