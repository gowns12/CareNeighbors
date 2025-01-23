package careneighbors.hospital;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/hospitals")
public class HospitalController {

    private final HospitalService hospitalService;

    @Autowired
    public HospitalController(HospitalService hospitalService) {
        this.hospitalService = hospitalService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void createHospital(@RequestBody HospitalRequest rq) {
        hospitalService.createHospital(rq);
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<HospitalResponse> getAllHospitals() {
        return hospitalService.getAllHospitals();
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public HospitalResponse getHospitalById(@PathVariable Long id) {
        return hospitalService.getHospitalById(id);
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void updateHospital(@PathVariable Long id, @RequestBody HospitalRequest rq) {
        hospitalService.updateHospital(id, rq);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteHospital(@PathVariable Long id) {
        hospitalService.deleteHospital(id);
    }
}