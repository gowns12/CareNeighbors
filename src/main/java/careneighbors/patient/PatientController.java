package careneighbors.patient;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("careneighbors/patient")
public class PatientController {

    private final PatientService patientService;

    @PostMapping
    public void createPatient(@RequestBody PatientRequest rq){
        patientService.create(rq);
    }

    @GetMapping
    public List<PatientResponse> readAllPatients(){
        return patientService.readAll();
    }

    @GetMapping("/{patientId}")
    public PatientResponse readPatient(@PathVariable Long patientId){
        return patientService.read(patientId);
    }

    @PutMapping("/{patientId}")
    public void updatePatient(@PathVariable Long patientId, @RequestBody PatientRequest rq){
        patientService.update(patientId, rq);
    }

    @DeleteMapping("/{patientId}")
    public void deletePatient(@PathVariable Long patientId){
        patientService.delete(patientId);
    }
}
