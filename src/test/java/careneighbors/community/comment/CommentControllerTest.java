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
    public void testReadComment() throws Exception {
        // Mock 데이터 생성
        CommentResponse mockResponse = new CommentResponse(
                1L,
                PatientStatusType.STABLE,
                "추가 설명",
                "Author",
                null,
                null,
                null,
                "path/to/image.jpg" // 이미지 경로 추가
        );

        // Mock 동작 정의
        when(commentService.read(1L)).thenReturn(mockResponse);

        // API 호출 및 검증
        mockMvc.perform(get("/api/comments/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.patientStatus").value("STABLE"))
                .andExpect(jsonPath("$.additionalContent").value("추가 설명"))
                .andExpect(jsonPath("$.imagePath").value("path/to/image.jpg")); // 이미지 경로 검증 추가
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
