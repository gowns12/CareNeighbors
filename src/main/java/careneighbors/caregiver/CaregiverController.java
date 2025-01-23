package careneighbors.caregiver;

import careneighbors.caregiver.Caregiver;
import careneighbors.caregiver.CaregiverDTO;
import careneighbors.caregiver.CaregiverService;
import careneighbors.caregiver.exception.CaregiverNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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
    @ResponseStatus(HttpStatus.CREATED)
    public void createCaregiver(@RequestBody CaregiverDTO caregiverDTO) {
        caregiverService.createCaregiver(caregiverDTO);
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<Caregiver> getAllCaregivers() {
        return caregiverService.getAllCaregivers();
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Caregiver getCaregiverById(@PathVariable Long id) {
        return caregiverService.getCaregiverById(id);
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void updateCaregiver(@PathVariable Long id, @RequestBody CaregiverDTO caregiverDTO) {
        caregiverService.updateCaregiver(id, caregiverDTO);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteCaregiver(@PathVariable Long id) {
        caregiverService.deleteCaregiver(id);
    }
}