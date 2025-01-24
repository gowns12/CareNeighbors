package careneighbors.community.post;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Arrays;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


public class PostControllerTest {

    @Mock
    private PostService postService;

    @InjectMocks
    private PostController postController;

    private MockMvc mockMvc;
    private ObjectMapper objectMapper;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(postController).build();
        objectMapper = new ObjectMapper();

    }

    @Test
    public void testCreatePost() throws Exception {
        PostResponse mockResponse = new PostResponse(1L, "Test post", "Content", "Author", null, null, null, null);
        when(postService.create(any(PostRequest.class))).thenReturn(mockResponse);

        mockMvc.perform(post("/api/posts")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"title\":\"Test post\",\"content\":\"Content\",\"authorName\":\"Author\"}"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.title").value("Test post"));
    }

    @Test
    public void testReadAllPosts() throws Exception {
        PostResponse mockResponse = new PostResponse(1L, "Test post", "Content", "Author", null, null, null, null);
        when(postService.readAll()).thenReturn(Arrays.asList(mockResponse));

        mockMvc.perform(get("/api/posts"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].id").value(1))
                .andExpect(jsonPath("$[0].title").value("Test post"));
    }

    @Test
    public void testReadPost() throws Exception {
        PostResponse mockResponse = new PostResponse(1L, "Test post", "Content", "Author", null, null, null, null);
        when(postService.read(1L)).thenReturn(mockResponse);

        mockMvc.perform(get("/api/posts/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.title").value("Test post"));
    }

    @Test
    public void testUpdatePost() throws Exception {
        mockMvc.perform(put("/api/posts/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"title\":\"Updated post\",\"content\":\"Updated content\"}"))
                .andExpect(status().isOk());
    }

    @Test
    public void testDeletePost() throws Exception {
        mockMvc.perform(delete("/api/posts/1"))
                .andExpect(status().isOk());
    }
}
