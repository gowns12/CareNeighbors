package careneighbors.community.comment;

public record CommentRequest(

        PatientStatusType patientStatus,
        String additionalContent,
        String authorName,
        Long postId,
        Long guardianCommentId
) {
}