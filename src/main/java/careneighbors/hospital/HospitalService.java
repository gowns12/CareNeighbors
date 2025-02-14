package careneighbors.hospital;

import careneighbors.AgeUtils;
import careneighbors.hospital.hospitalDto.HospitalRequest;
import careneighbors.hospital.hospitalDto.HospitalResponse;
import careneighbors.patient.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.NoSuchElementException;

@RequiredArgsConstructor
@Service
public class HospitalService {

    private final HospitalBillRepository hospitalBillRepository;
    private final HospitalRepository hospitalRepository;
    private final PatientHospitalRepository patientHospitalRepository;
    private final PatientRepository patientRepository;

    //Todo 병원 생성
    public void createHospital(HospitalRequest hospitalRequest) {
        hospitalRepository.save(new Hospital(
                hospitalRequest.companyName(),
                hospitalRequest.address(),
                hospitalRequest.contactNumber(),
                hospitalRequest.registrationNumber(),
                hospitalRequest.type(),
                hospitalRequest.bedCount(),
                hospitalRequest.website(),
                hospitalRequest.imageUrl()
        ));
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
        return hospitalRepository.findByCompanyNameContaining(name);
    }

    //Todo 병원 id  조회
    public HospitalResponse getHospitalByNameAndId(Long id) {
        Hospital hospital = hospitalRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("hospital not found"));
        return HospitalResponse.toDto(hospital);
    }

    //Todo 병원 수정
    @Transactional
    public void updateHospital(Long id, HospitalRequest hospitalRequest) {
        Hospital hospital = hospitalRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Hospital not found with id: " + id));

        hospital.update(
                hospitalRequest.companyName(),
                hospitalRequest.address(),
                hospitalRequest.contactNumber(),
                hospitalRequest.registrationNumber(),
                hospitalRequest.type(),
                hospitalRequest.bedCount(),
                hospitalRequest.website(),
                hospitalRequest.imageUrl()
        );
    }

    //Todo 병원 삭제
    public void deleteHospital(Long id) {
        if (!hospitalRepository.existsById(id)) {
            throw new IllegalArgumentException("Hospital not found with id: " + id);
        }
        hospitalRepository.deleteById(id);
    }

    //TODO 환자 iD 로 조회
    public PatientHospital getPatientsById(Long hospitalId, Long patientId) {
        Hospital hospital = hospitalRepository.findById(hospitalId).orElseThrow(
                () -> new IllegalArgumentException("찾을수 없는 id"));
        return patientHospitalRepository.findById(patientId).orElseThrow(
                () -> new IllegalArgumentException("없는 환자입니다."));
    }

    //Todo 환자 이름으로 조회
    public List<PatientResponse> getPatients(String name) {
        return patientRepository.findByNameContaining(name).stream().map(
                p -> new PatientResponse(
                        p.getId(),
                        p.getName(),
                        AgeUtils.calculateKoreanAge(p.getResidentNumber()),
                        p.getGender(),
                        p.getResidentNumber(),
                        p.getPhoneNumber(),
                        p.getMedicalConditions())
        ).toList();
    }

    //Todo 병원비 내역 생성
    public void generateHospitalBill(Long hospitalId, Long patientId, double treatmentCost, double roomCharge, double careCost) {
        Hospital hospital = hospitalRepository.findById(hospitalId)
                .orElseThrow(() -> new IllegalArgumentException("병원 id 를 찾을 수 없습니다."));

        Patient patient = patientRepository.findById(patientId).orElseThrow(
                () -> new IllegalArgumentException("환자 id 를 찾을 수 없습니다."));

        hospitalBillRepository.save(new HospitalBill(
                        hospital,
                        patient,
                        treatmentCost,
                        roomCharge,
                        careCost
                )
        );
    }

    //Todo 병원비 내역 조회
    public HospitalBill getHospitalBillByPatientId(Long hospitalId, Long patientId) {

        Hospital hospital = hospitalRepository.findById(hospitalId)
                .orElseThrow(() -> new IllegalArgumentException("병원 id 를 찾을 수 없습니다."));
        Patient patient = patientRepository.findById(patientId)
                .orElseThrow(() -> new IllegalArgumentException("환자 id 를 찾을 수 없습니다."));
        HospitalBill hospitalBill = hospitalBillRepository.findByHospitalIdAndPatientId(hospital.getId(), patient.getId());
        return new HospitalBill(hospitalBill.getHospital(),
                hospitalBill.getPatient(),
                hospitalBill.getTotalCost());
    }

}
