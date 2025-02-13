package careneighbors.community.comment;

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

public class CommentControllerTest {

    @Mock
    private CommentService commentService;

    @InjectMocks
    private CommentController commentController;

    private MockMvc mockMvc;
    private ObjectMapper objectMapper;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(commentController).build();
        objectMapper = new ObjectMapper();
    }

    @Test
    public void testCreateComment() throws Exception {
        CommentResponse mockResponse = new CommentResponse(1L, PatientStatusType.STABLE, "추가 설명", "Author", null, null, null);
        when(commentService.create(any(CommentRequest.class))).thenReturn(mockResponse);

        mockMvc.perform(post("/api/comments")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"patientStatus\":\"STABLE\",\"additionalContent\":\"추가 설명\",\"authorName\":\"Author\",\"postId\":1}"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.patientStatus").value("STABLE"))
                .andExpect(jsonPath("$.additionalContent").value("추가 설명"));
    }

    @Test
    public void testReadComment() throws Exception {
        CommentResponse mockResponse = new CommentResponse(1L, PatientStatusType.STABLE, "추가 설명", "Author", null, null, null);
        when(commentService.read(1L)).thenReturn(mockResponse);

        mockMvc.perform(get("/api/comments/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.patientStatus").value("STABLE"))
                .andExpect(jsonPath("$.additionalContent").value("추가 설명"));
    }

    @Test
    public void testUpdateComment() throws Exception {
        mockMvc.perform(put("/api/comments/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"patientStatus\":\"IMPROVING\",\"additionalContent\":\"상태가 호전되고 있습니다\"}"))
                .andExpect(status().isOk());
    }

    @Test
    public void testDeleteComment() throws Exception {
        mockMvc.perform(delete("/api/comments/1"))
                .andExpect(status().isOk());
    }
}
