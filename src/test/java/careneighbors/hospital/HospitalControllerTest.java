package careneighbors.hospital;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class HospitalControllerTest {

    @Mock
    private HospitalService hospitalService;

    @InjectMocks
    private HospitalController hospitalController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testCreateHospital() {
        HospitalDTO hospitalDTO = new HospitalDTO("Hospital A", "Seoul", "02-1234-5678", 12345, "General", 100, "www.hospitala.com", "image.jpg");
        Hospital createdHospital = HospitalDTO.toEntity(hospitalDTO);
        createdHospital.setId(1L);

        when(hospitalService.createHospital(hospitalDTO)).thenReturn(createdHospital);

        ResponseEntity<Hospital> response = hospitalController.createHospital(hospitalDTO);

        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals(createdHospital, response.getBody());
    }

    @Test
    void testGetAllHospitals() {
        List<Hospital> hospitals = Arrays.asList(new Hospital(), new Hospital());
        when(hospitalService.getAllHospitals()).thenReturn(hospitals);

        ResponseEntity<List<Hospital>> response = hospitalController.getAllHospitals();

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(hospitals, response.getBody());
    }

    @Test
    void testGetHospitalById() {
        Long id = 1L;
        Hospital hospital = new Hospital();
        hospital.setId(id);

        when(hospitalService.getHospitalById(id)).thenReturn(hospital);

        ResponseEntity<Hospital> response = hospitalController.getHospitalById(id);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(hospital, response.getBody());
    }

    @Test
    void testUpdateHospital() {
        Long id = 1L;
        HospitalDTO hospitalDTO = new HospitalDTO("Hospital B", "Busan", "051-9876-5432", 54321, "Specialized", 200, "www.hospitalb.com", "new_image.jpg");
        Hospital updatedHospital = HospitalDTO.toEntity(hospitalDTO);
        updatedHospital.setId(id);

        when(hospitalService.updateHospital(id, hospitalDTO)).thenReturn(updatedHospital);

        ResponseEntity<Hospital> response = hospitalController.updateHospital(id, hospitalDTO);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(updatedHospital, response.getBody());
    }

    @Test
    void testDeleteHospital() {
        Long id = 1L;

        ResponseEntity<Void> response = hospitalController.deleteHospital(id);

        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
        verify(hospitalService, times(1)).deleteHospital(id);
    }
}
