package careneighbors.patient;

import careneighbors.AgeUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PatientService {

    private final PatientRepository patientRepository;

    public void create(PatientRequest rq) {
        Patient patient = new Patient(
                rq.name(),
                rq.gender(),
                rq.residentNumber(),
                rq.phoneNumber(),
                rq.medicalConditions()
        );
        patientRepository.save(patient);
    }


    public List<PatientResponse> readAll() {
        return patientRepository.findAll().stream()
                .map(o -> new PatientResponse(
                        o.getId(),
                        o.getName(),
                        AgeUtils.calculateKoreanAge(o.getResidentNumber()),
                        o.getGender(),
                        o.getResidentNumber(),
                        o.getPhoneNumber(),
                        o.getMedicalConditions()

                )).toList();
    }

    public PatientResponse read(Long patientId) {
        Patient patient = patientRepository.findById(patientId).orElseThrow(() -> new PatientMisMatchException("환자가 존재하지 않습니다."));
        return new PatientResponse(
                patient.getId(),
                patient.getName(),
                AgeUtils.calculateKoreanAge(patient.getResidentNumber()),
                patient.getGender(),
                patient.getResidentNumber(),
                patient.getPhoneNumber(),
                patient.getMedicalConditions()
        );
    }

    @Transactional
    public void update(Long patientId, PatientRequest rq) {
        Patient patient = patientRepository.findById(patientId).orElseThrow(() -> new PatientMisMatchException("환자가 존재하지 않습니다."));
        patient.update(
                rq.name(),
                rq.gender(),
                rq.residentNumber(),
                rq.phoneNumber(),
                rq.medicalConditions()
        );
    }

    public void delete(Long patientId) {
        patientRepository.deleteById(patientId);
    }
}

