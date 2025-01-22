package careneighbors.caregiver;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class CaregiverControllerTest {

    @Mock
    private CaregiverService caregiverService;

    @InjectMocks
    private CaregiverController caregiverController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testCreateCaregiver() {
        CaregiverDTO caregiverDTO = new CaregiverDTO("Korean", "Korean", "Hospital A", "Seoul", "John Doe", 12345, 30, "010-1234-5678", "Seoul, Korea", "image.jpg", false);
        Caregiver createdCaregiver = CaregiverDTO.toEntity(caregiverDTO);
        createdCaregiver.setId(1L);

        when(caregiverService.createCaregiver(caregiverDTO)).thenReturn(createdCaregiver);

        ResponseEntity<Caregiver> response = caregiverController.createCaregiver(caregiverDTO);

        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals(createdCaregiver, response.getBody());
    }

    @Test
    void testGetAllCaregivers() {
        List<Caregiver> caregivers = Arrays.asList(
                new Caregiver(), new Caregiver()
        );

        when(caregiverService.getAllCaregivers()).thenReturn(caregivers);

        ResponseEntity<List<Caregiver>> response = caregiverController.getAllCaregivers();

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(caregivers, response.getBody());
    }

    @Test
    void testGetCaregiverById() {
        Long id = 1L;
        Caregiver caregiver = new Caregiver();
        caregiver.setId(id);

        when(caregiverService.getCaregiverById(id)).thenReturn(caregiver);

        ResponseEntity<Caregiver> response = caregiverController.getCaregiverById(id);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(caregiver, response.getBody());
    }

    @Test
    void testUpdateCaregiver() {
        Long id = 1L;
        CaregiverDTO caregiverDTO = new CaregiverDTO("Korean", "Korean", "Hospital B", "Busan", "Jane Doe", 54321, 35, "010-8765-4321", "Busan, Korea", "new_image.jpg", false);
        Caregiver updatedCaregiver = CaregiverDTO.toEntity(caregiverDTO);
        updatedCaregiver.setId(id);

        when(caregiverService.updateCaregiver(id, caregiverDTO)).thenReturn(updatedCaregiver);

        ResponseEntity<Caregiver> response = caregiverController.updateCaregiver(id, caregiverDTO);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(updatedCaregiver, response.getBody());
    }

    @Test
    void testDeleteCaregiver() {
        Long id = 1L;

        ResponseEntity<Void> response = caregiverController.deleteCaregiver(id);

        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
        verify(caregiverService, times(1)).deleteCaregiver(id);
    }
}
