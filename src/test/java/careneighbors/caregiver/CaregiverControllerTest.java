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
    private CaregiverControllerTest caregiverController;

    private MockMvc mockMvc;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(caregiverController).build();
    }

    @Test
    void testCreateCaregiver() throws Exception {
        CaregiverDTO caregiverDTO = new CaregiverDTO("Korean", "Korean", "Hospital A", "Seoul", "John Doe", 12345, 30, "010-1234-5678", "Seoul, Korea", "image.jpg", false);

        doNothing().when(caregiverService).createCaregiver(any(CaregiverDTO.class));

        mockMvc.perform(post("/api/caregivers")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(new ObjectMapper().writeValueAsString(caregiverDTO)))
                .andExpect(status().isCreated());

        verify(caregiverService, times(1)).createCaregiver(any(CaregiverDTO.class));
    }

    @Test
    void testGetAllCaregivers() throws Exception {
        List<Caregiver> caregivers = Arrays.asList(new Caregiver(), new Caregiver());
        when(caregiverService.getAllCaregivers()).thenReturn(caregivers);

        mockMvc.perform(get("/api/caregivers"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$", hasSize(2)));
    }

    @Test
    void testGetCaregiverById() throws Exception {
        Long id = 1L;
        Caregiver caregiver = new Caregiver();
        caregiver.setId(id);
        when(caregiverService.getCaregiverById(id)).thenReturn(caregiver);

        mockMvc.perform(get("/api/caregivers/{id}", id))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id").value(id));
    }

    @Test
    void testUpdateCaregiver() throws Exception {
        Long id = 1L;
        CaregiverDTO caregiverDTO = new CaregiverDTO("Korean", "Korean", "Hospital B", "Busan", "Jane Doe", 54321, 35, "010-8765-4321", "Busan, Korea", "new_image.jpg", false);

        doNothing().when(caregiverService).updateCaregiver(eq(id), any(CaregiverDTO.class));

        mockMvc.perform(put("/api/caregivers/{id}", id)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(new ObjectMapper().writeValueAsString(caregiverDTO)))
                .andExpect(status().isOk());

        verify(caregiverService, times(1)).updateCaregiver(eq(id), any(CaregiverDTO.class));
    }

    @Test
    void testDeleteCaregiver() throws Exception {
        Long id = 1L;
        doNothing().when(caregiverService).deleteCaregiver(id);

        mockMvc.perform(delete("/api/caregivers/{id}", id))
                .andExpect(status().isNoContent());

        verify(caregiverService, times(1)).deleteCaregiver(id);
    }
}
