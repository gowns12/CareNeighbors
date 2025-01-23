package careneighbors.hospital;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/hospitals")
public class HospitalController {

    private final HospitalService hospitalService;

    @PostMapping
    public HospitalResponse createHospital(@RequestBody HospitalRequest rq) {
        return hospitalService.create(rq);
    }

    @GetMapping
    public List<HospitalResponse> readAllHospitals() {
        return hospitalService.readAll();
    }

    @GetMapping("/{hospitalId}")
    public HospitalResponse readHospital(@PathVariable Long hospitalId) {
        return hospitalService.read(hospitalId);
    }

    @PutMapping("/{hospitalId}")
    public void updateHospital(@PathVariable Long hospitalId, @RequestBody HospitalRequest rq) {
        hospitalService.update(hospitalId, rq);
    }

    @DeleteMapping("/{hospitalId}")
    public void deleteHospital(@PathVariable Long hospitalId) {
        hospitalService.delete(hospitalId);
    }
}
