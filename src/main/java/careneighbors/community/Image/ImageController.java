package careneighbors.community.Image;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/images")
public class ImageController {

    private final ImageService imageService;

    @PostMapping
    public ImageResponse uploadImage(@RequestBody ImageRequest rq) {
        return imageService.upload(rq);
    }

    @DeleteMapping("/{imageId}")
    public void deleteImage(@PathVariable Long imageId) {
        imageService.delete(imageId);
    }
}