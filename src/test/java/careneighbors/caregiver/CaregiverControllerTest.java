package careneighbors.caregiver;

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
import java.util.List;

import static org.hamcrest.Matchers.hasSize;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

class CaregiverControllerTest {

    @Mock
    private CaregiverService caregiverService;

    @InjectMocks
    private CaregiverController caregiverController;

    private MockMvc mockMvc;
    private ObjectMapper objectMapper;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(caregiverController).build();
        objectMapper = new ObjectMapper();
    }

    @Test
    void testCreateCaregiver() throws Exception {
        CaregiverRequest caregiverRequest = new CaregiverRequest("Korean", "Korean", "Hospital A", "Seoul", "John Doe", "12345", "010-1234-5678", "Seoul, Korea", "image.jpg");
        CaregiverResponse caregiverResponse = new CaregiverResponse(1L, "Korean", "Korean", "Hospital A", "Seoul", "John Doe", "12345", "010-1234-5678", "Seoul, Korea", "image.jpg");

        when(caregiverService.create(any(CaregiverRequest.class))).thenReturn(caregiverResponse);

        mockMvc.perform(post("/api/caregivers")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(caregiverRequest)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1L));

        verify(caregiverService, times(1)).create(any(CaregiverRequest.class));
    }

    @Test
    void testReadAllCaregivers() throws Exception {
        List<CaregiverResponse> caregivers = Arrays.asList(
                new CaregiverResponse(1L, "Korean", "Korean", "Hospital A", "Seoul", "John Doe", "12345", "010-1234-5678", "Seoul, Korea", "image1.jpg"),
                new CaregiverResponse(2L, "English", "English", "Hospital B", "Busan", "Jane Doe", "67890", "010-9876-5432", "Busan, Korea", "image2.jpg")
        );
        when(caregiverService.readAll()).thenReturn(caregivers);

        mockMvc.perform(get("/api/caregivers"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$", hasSize(2)));
    }

    @Test
    void testReadCaregiver() throws Exception {
        Long id = 1L;
        CaregiverResponse caregiverResponse = new CaregiverResponse(id, "Korean", "Korean", "Hospital A", "Seoul", "John Doe", "12345", "010-1234-5678", "Seoul, Korea", "image.jpg");
        when(caregiverService.read(id)).thenReturn(caregiverResponse);

        mockMvc.perform(get("/api/caregivers/{id}", id))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id").value(id));
    }

    @Test
    void testUpdateCaregiver() throws Exception {
        Long id = 1L;
        CaregiverRequest caregiverRequest = new CaregiverRequest("Korean", "Korean", "Hospital B", "Busan", "Jane Doe", "54321", "010-8765-4321", "Busan, Korea", "new_image.jpg");

        mockMvc.perform(put("/api/caregivers/{id}", id)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(caregiverRequest)))
                .andExpect(status().isOk());

        verify(caregiverService, times(1)).update(eq(id), any(CaregiverRequest.class));
    }

    @Test
    void testDeleteCaregiver() throws Exception {
        Long id = 1L;

        mockMvc.perform(delete("/api/caregivers/{id}", id))
                .andExpect(status().isOk());

        verify(caregiverService, times(1)).delete(id);
    }
}
