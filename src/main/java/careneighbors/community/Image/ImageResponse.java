package careneighbors.community.Image;

import java.time.LocalDateTime;

public record ImageResponse(
        Long id,
        String url,
        LocalDateTime uploadedAt
) {
    public static ImageResponse toDto(Image image) {
        return new ImageResponse(
                image.getId(),
                image.getUrl(),
                image.getUploadedAt()
        );
    }
}