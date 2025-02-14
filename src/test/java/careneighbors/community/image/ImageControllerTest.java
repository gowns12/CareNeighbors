package careneighbors.community.image;

import careneighbors.community.Image.ImageController;
import careneighbors.community.Image.ImageRequest;
import careneighbors.community.Image.ImageResponse;
import careneighbors.community.Image.ImageService;
import careneighbors.community.post.PostController;
import careneighbors.community.post.PostService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


public class ImageControllerTest {

    @Mock
    private ImageService imageService;

    @InjectMocks
    private ImageController imageController;

    private MockMvc mockMvc;
    private ObjectMapper objectMapper;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(imageController).build();
        objectMapper = new ObjectMapper();

    }
    @Test
    public void testUploadImage() throws Exception {
        ImageResponse mockResponse = new ImageResponse(1L, "http://example.com/image.jpg", null);
        when(imageService.upload(any(ImageRequest.class))).thenReturn(mockResponse);

        mockMvc.perform(post("/api/images")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"url\":\"http://example.com/image.jpg\",\"postId\":1}"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.url").value("http://example.com/image.jpg"));
    }

    @Test
    public void testDeleteImage() throws Exception {
        mockMvc.perform(delete("/api/images/1"))
                .andExpect(status().isOk());
    }
}
