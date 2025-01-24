package careneighbors.hospital;

import careneighbors.hospital.hospitalDto.HospitalRequest;
import careneighbors.hospital.hospitalDto.HospitalResponse;
import careneighbors.patient.Patient;
import careneighbors.patient.PatientRepository;
import jakarta.persistence.Id;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.NoSuchElementException;

@Service
public class HospitalService {

    private final HospitalRepository hospitalRepository;
    private final PatientRepository patientRepository;

    @Autowired
    public HospitalService(HospitalRepository hospitalRepository, PatientRepository patientRepository) {
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

    //TOdo 병원 목록 조회 (null 이면 전체목록 name 값이 들어가면 포함하는 값을 반환)
    public List<HospitalResponse> getAllHospitals(String name) {
        if (name == null) {
            return hospitalRepository.findAll()
                    .stream()
                    .map(HospitalResponse::toDto
                    )
                    .toList();
        }
        return hospitalRepository.findByNameContaining(name);
    }

    //Todo 병원 id And Name 조회
    public HospitalResponse getHospitalByNameAndId(Long id) {
        Hospital hospital = hospitalRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("hospital not found"));
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
//    //Todo 병원비내역생성
//    public void makebill(String hospitalName , Long id) {
//        Hospital hospital = hospitalRepository.findByName(hospitalName);
//        Patient patientNotFound = patientRepository.findById(id).orElseThrow(
//                () -> new IllegalArgumentException("Patient not found"));
//    }
}
