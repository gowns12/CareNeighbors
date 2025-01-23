package careneighbors.hospital;

import careneighbors.hospital.hospitalDto.HospitalRequest;
import careneighbors.hospital.hospitalDto.HospitalResponse;
import careneighbors.patient.Patient;
import careneighbors.patient.PatientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class HospitalService {

    private final HospitalRepository hospitalRepository;
    private final PatientRepository patientRepository;

    @Autowired
    public HospitalService(HospitalRepository hospitalRepository ,PatientRepository patientRepository) {
        this.hospitalRepository = hospitalRepository;
        this.patientRepository = patientRepository;
    }

    //Todo 병원 생성
    public void createHospital(HospitalRequest hospitalRequest) {
        Hospital hospital = new Hospital(
                hospitalRequest.companyName(),
                hospitalRequest.address(),
                hospitalRequest.contactNumber(),
                hospitalRequest.registrationNumber(),
                hospitalRequest.type(),
                hospitalRequest.bedCount()
        );
        hospitalRepository.save(hospital);
    }

    //TOdo 병원 목록 조회
    public List<HospitalResponse> getAllHospitals() {
        return hospitalRepository.findAll().stream()
                .map(HospitalResponse::toDto
                )
                .toList();
    }
    //Todo 병원 id 로 조회
    public HospitalResponse getHospitalById(Long id) {
        Hospital hospital = hospitalRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Hospital not found with id: " + id));
        return HospitalResponse.toDto(hospital);
    }
    //Todo 병원 수정
    @Transactional
    public void updateHospital(Long id, HospitalRequest rq) {
        Hospital hospital = hospitalRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Hospital not found with id: " + id));
        hospital.update(
                rq.companyName(),
                rq.address(),
                rq.contactNumber(),
                rq.bedCount(),
                rq.website(),
                rq.imageUrl());
    }
    //Todo 병원 삭제
    public void deleteHospital(Long id) {
        if (!hospitalRepository.existsById(id)) {
            throw new IllegalArgumentException("Hospital not found with id: " + id);
        }
        hospitalRepository.deleteById(id);
    }
    //Todo 병원비내역생성
    public void makebill(String hospitalName , Long id) {
        Hospital hospital = hospitalRepository.findByName(hospitalName);
        Patient patientNotFound = patientRepository.findById(id).orElseThrow(
                () -> new IllegalArgumentException("Patient not found"));
    }
}
