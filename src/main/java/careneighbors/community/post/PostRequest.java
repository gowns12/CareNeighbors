package careneighbors.community.post;

public record PostRequest(
        String title,
        String content,
        String authorName
) {
}