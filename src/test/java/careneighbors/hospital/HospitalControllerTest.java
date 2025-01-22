package careneighbors.hospital;


import careneighbors.hospital.exception.HospitalNotFoundException;
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

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

class HospitalControllerTest {

    @Mock
    private HospitalService hospitalService;

    @InjectMocks
    private HospitalController hospitalController;

    private MockMvc mockMvc;
    private ObjectMapper objectMapper;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(hospitalController).build();
        objectMapper = new ObjectMapper();
    }

    @Test
    void testCreateHospital() throws Exception {
        HospitalDTO hospitalDTO = new HospitalDTO("Hospital A", "Seoul", "02-1234-5678", 12345, "General", 100, "www.hospitala.com", "image.jpg");

        doNothing().when(hospitalService).createHospital(any(HospitalDTO.class));

        mockMvc.perform(post("/api/hospitals")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(hospitalDTO)))
                .andExpect(status().isCreated());

        verify(hospitalService, times(1)).createHospital(any(HospitalDTO.class));
    }

    @Test
    void testGetAllHospitals() throws Exception {
        List<Hospital> hospitals = Arrays.asList(new Hospital(), new Hospital());
        when(hospitalService.getAllHospitals()).thenReturn(hospitals);

        mockMvc.perform(get("/api/hospitals"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$").isArray())
                .andExpect(jsonPath("$.length()").value(2));
    }

    @Test
    void testGetHospitalById() throws Exception {
        Long id = 1L;
        Hospital hospital = new Hospital();
        hospital.setId(id);
        when(hospitalService.getHospitalById(id)).thenReturn(hospital);

        mockMvc.perform(get("/api/hospitals/{id}", id))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id").value(id));
    }

    @Test
    void testUpdateHospital() throws Exception {
        Long id = 1L;
        HospitalDTO hospitalDTO = new HospitalDTO("Hospital B", "Busan", "051-9876-5432", 54321, "Specialized", 200, "www.hospitalb.com", "new_image.jpg");

        doNothing().when(hospitalService).updateHospital(eq(id), any(HospitalDTO.class));

        mockMvc.perform(put("/api/hospitals/{id}", id)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(hospitalDTO)))
                .andExpect(status().isOk());

        verify(hospitalService, times(1)).updateHospital(eq(id), any(HospitalDTO.class));
    }

    @Test
    void testDeleteHospital() throws Exception {
        Long id = 1L;
        doNothing().when(hospitalService).deleteHospital(id);

        mockMvc.perform(delete("/api/hospitals/{id}", id))
                .andExpect(status().isNoContent());

        verify(hospitalService, times(1)).deleteHospital(id);
    }

    @Test
    void testHandleHospitalNotFoundException() throws Exception {
        Long id = 1L;
        when(hospitalService.getHospitalById(id)).thenThrow(new HospitalNotFoundException("Hospital not found"));

        mockMvc.perform(get("/api/hospitals/{id}", id))
                .andExpect(status().isNotFound())
                .andExpect(content().string("Hospital not found"));
    }
}
