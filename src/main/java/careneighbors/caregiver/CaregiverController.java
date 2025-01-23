package careneighbors.caregiver;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/caregivers")
public class CaregiverController {

    private final CaregiverService caregiverService;

    @PostMapping
    public CaregiverResponse createCaregiver(@RequestBody CaregiverRequest rq) {
        return caregiverService.create(rq);
    }

    @GetMapping
    public List<CaregiverResponse> readAllCaregivers() {
        return caregiverService.readAll();
    }

    @GetMapping("/{caregiverId}")
    public CaregiverResponse readCaregiver(@PathVariable Long caregiverId) {
        return caregiverService.read(caregiverId);
    }

    @PutMapping("/{caregiverId}")
    public void updateCaregiver(@PathVariable Long caregiverId, @RequestBody CaregiverRequest rq) {
        caregiverService.update(caregiverId, rq);
    }

    @DeleteMapping("/{caregiverId}")
    public void deleteCaregiver(@PathVariable Long caregiverId) {
        caregiverService.delete(caregiverId);
    }
}
