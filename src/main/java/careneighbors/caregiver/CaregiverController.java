package careneighbors.caregiver;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

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
    public CaregiverResponse createCaregiver(@RequestBody CaregiverRequest caregiverRequest) {
        Caregiver caregiver = caregiverService.createCaregiver(caregiverRequest);
        return CaregiverResponse.toDto(caregiver);
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<CaregiverResponse> getAllCaregivers() {
        return caregiverService.getAllCaregivers().stream()
                .map(CaregiverResponse::toDto)
                .collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public CaregiverResponse getCaregiverById(@PathVariable Long id) {
        Caregiver caregiver = caregiverService.getCaregiverById(id);
        return CaregiverResponse.toDto(caregiver);
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public CaregiverResponse updateCaregiver(@PathVariable Long id, @RequestBody CaregiverRequest caregiverRequest) {
        Caregiver updatedCaregiver = caregiverService.updateCaregiver(id, caregiverRequest);
        return CaregiverResponse.toDto(updatedCaregiver);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteCaregiver(@PathVariable Long id) {
        caregiverService.deleteCaregiver(id);
    }
}