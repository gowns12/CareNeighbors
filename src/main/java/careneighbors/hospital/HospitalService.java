package careneighbors.hospital;

import careneighbors.guardian.Guardian;
import careneighbors.guardian.GuardianRepository;
import careneighbors.guardian.GuardianResponse;
import careneighbors.hospital.hospitalDto.HospitalRequest;
import careneighbors.hospital.hospitalDto.HospitalResponse;
import careneighbors.patient.*;
import jakarta.persistence.Id;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.NoSuchElementException;

@RequiredArgsConstructor
@Service
public class HospitalService {

    private final HospitalRepository hospitalRepository;
    private final PatientHospitalRepository patientHospitalRepository;
    private final PatientRepository patientRepository;
    private final GuardianRepository guardianRepository;

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
    //Todo 환자 등록
    public void registerPatient(Long id, PatientRequest patient) {
        Hospital hospital = hospitalRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("hospital id not found"));
        PatientHospital patientHospital = new PatientHospital(patient,hospital);
        patientHospitalRepository.save(patientHospital);
    }

    //Todo 환자 조회
    public List<PatientResponse> getPatients(String name) {
       return patientRepository.findByNameContaining(name).stream().map(
                p -> new PatientResponse(
                        p.getId(),
                        p.getName(),
                        p.getAge(),
                        p.getGender(),
                        p.getResidentNumber(),
                        p.getPhoneNumber(),
                        p.getMedicalConditions())
        ).toList();
    }

//    //Todo 보호자 리스트
//    public List<GuardianResponse> getGuardianList(Long namse) {
//        return guardianRepository.findAll().stream()
//                .map(p->new GuardianResponse(
//                        p.getId(),
//                        p.getName(),
//                        p.getPhoneNumber(),
//                        p.getResidentNumber(),
//                        p.getLocation())).toList();
//    }

    //Todo 병원비 내역
    public void generateHospitalBill(Long hospitalId, Long guardianId, double treatmentCost, double roomCharge , double careCost) {
        Hospital hospital = hospitalRepository.findById(hospitalId)
                .orElseThrow(() -> new IllegalArgumentException("Cannot found id"));

        Guardian guardian = guardianRepository.findById(guardianId)
                .orElseThrow(() -> new IllegalArgumentException("Cannot found id"));

        //Todo 병원비 계산
        HospitalBill hospitalBill = new HospitalBill(guardian, treatmentCost, roomCharge, careCost);
        //TOdo 병원에 병원비 내역 추가
        hospital.addHospitalBill(hospitalBill);
        //Todo 병원비 내역 저장
        hospitalRepository.save(hospital);
    }
}
