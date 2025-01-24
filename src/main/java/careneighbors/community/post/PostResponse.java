package careneighbors.community.post;

import careneighbors.community.Image.ImageResponse;
import careneighbors.community.comment.CommentResponse;

import java.time.LocalDateTime;
import java.util.List;

public record PostResponse(
        Long id,
        String title,
        String content,
        String authorName,
        LocalDateTime createdAt,
        LocalDateTime updatedAt,
        List<CommentResponse> comments,
        List<ImageResponse> images
) {
    public static PostResponse toDto(Post post) {
        return new PostResponse(
                post.getId(),
                post.getTitle(),
                post.getContent(),
                post.getAuthorName(),
                post.getCreatedAt(),
                post.getUpdatedAt(),
                post.getComments().stream().map(CommentResponse::toDto).toList(),
                post.getImages().stream().map(ImageResponse::toDto).toList()
        );
    }
}