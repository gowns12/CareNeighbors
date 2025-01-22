package careneighbors.caregiver;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/caregivers")
public class CaregiverController {

    private final CaregiverService caregiverService;

    @Autowired
    public CaregiverController(CaregiverService caregiverService) {
        this.caregiverService = caregiverService;
    }

    @PostMapping
    public ResponseEntity<Caregiver> createCaregiver(@RequestBody CaregiverDTO caregiverDTO) {
        Caregiver createdCaregiver = caregiverService.createCaregiver(caregiverDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdCaregiver);
    }

    @GetMapping
    public ResponseEntity<List<Caregiver>> getAllCaregivers() {
        List<Caregiver> caregivers = caregiverService.getAllCaregivers();
        return ResponseEntity.ok(caregivers);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Caregiver> getCaregiverById(@PathVariable Long id) {
        Caregiver caregiver = caregiverService.getCaregiverById(id);
        return ResponseEntity.ok(caregiver);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Caregiver> updateCaregiver(@PathVariable Long id, @RequestBody CaregiverDTO caregiverDTO) {
        Caregiver updatedCaregiver = caregiverService.updateCaregiver(id, caregiverDTO);
        return ResponseEntity.ok(updatedCaregiver);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCaregiver(@PathVariable Long id) {
        caregiverService.deleteCaregiver(id);
        return ResponseEntity.noContent().build();
    }
}