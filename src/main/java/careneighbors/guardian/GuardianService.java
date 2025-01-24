package careneighbors.guardian;


import careneighbors.caregiver.*;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class GuardianService {

    private final GuardianRepository guardianRepository;

    private final CaregiverRepository caregiverRepository;

    private final GiftRepository giftRepository;

    private final GuardianPatientRepository guardianPatientRepository;

    private final CaregiverPatientRepository caregiverPatientRepository;

    public GuardianService(GuardianRepository guardianRepository, CaregiverRepository caregiverRepository, GiftRepository giftRepository, GuardianPatientRepository guardianPatientRepository, CaregiverPatientRepository caregiverPatientRepository) {
        this.guardianRepository = guardianRepository;
        this.caregiverRepository = caregiverRepository;
        this.giftRepository = giftRepository;
        this.guardianPatientRepository = guardianPatientRepository;
        this.caregiverPatientRepository = caregiverPatientRepository;
    }


    @Transactional
    public void createGuardian(CreateGuardianRequest request) {

        boolean guardianExists = guardianRepository.existsByResidentNumberOrPhoneNumber(
                request.residentNumber(), request.phoneNumber());

        if (guardianExists) {
            throw new IllegalArgumentException("이미 존재하는 보호자");
        }

        Guardian guardian = new Guardian(
                request.name(),
                request.residentNumber(),
                request.phoneNumber(),
                request.location()
        );

        guardianRepository.save(guardian);
    }

    //보호자 전체 조회
    public List<GuardianResponse> findAllGuardians() {
        List<Guardian> guardians = guardianRepository.findAll();

        return guardians.stream()
                .map(guardian -> new GuardianResponse(
                        guardian.getId(),
                        guardian.getName(),
                        guardian.getPhoneNumber(),
                        guardian.getResidentNumber(),
                        guardian.getLocation())
                ).toList();
    }


    //보호자 id로 특정 보호자 조회
    public GuardianResponse findGuardiansByGuardianId(Long guardianId) {
        Guardian guardian = guardianRepository.findById(guardianId)
                .orElseThrow(() -> new IllegalArgumentException("없는보호자"));

        return new GuardianResponse(guardian.getId(), guardian.getName(), guardian.getPhoneNumber(), guardian.getResidentNumber(), guardian.getLocation());
    }


    @Transactional
    public void updateGuardianRequest(Long guardianId, UpdateGuardianRequest request) {
        Guardian guardian = guardianRepository.findById(guardianId)
                .orElseThrow(() -> new IllegalArgumentException("없는보호자임"));

        Guardian updatedGuardian = new Guardian(
                request.name(),
                request.phoneNumber(),
                request.location(),
                request.ResidentNumber()


        );
        guardianRepository.save(updatedGuardian);

//        guardian.setName(request.name());
//        guardian.setResidentNumber(request.ResidentNumber());
//        guardian.setPhoneNumber(request.phoneNumber());
//        guardian.setLocation(request.location());
    }

    public void deleteByGuardianId(Long guardianId) {
        guardianRepository.deleteById(guardianId);
    }


    //보호자가 간병인 목록 조회
    public List<Caregiver> findAllCaregiver() {
        return caregiverRepository.findAll();

    }

    public String giftCaregiver(GiftRequest giftRequest) {
        Caregiver caregiver = caregiverRepository.findById(giftRequest.caregiverId())
                .orElseThrow(() -> new IllegalArgumentException("간병인 찾기 실패."));

        Gift gift = new Gift(caregiver, giftRequest.giftMessage());

        giftRepository.save(gift);

        return "선물이 성공적으로 전달되었습니다.";

    }

    public List<CaregiverPatientResponse> findCaregiverIdsByGuardian(Long guardianId) {
        // 1. 보호자 ID로 환자 목록 조회
        List<GuardianPatient> patients = guardianPatientRepository.findByGuardian_Id(guardianId);

        // 2. 환자 목록에서 환자 ID 추출
        List<Long> patientIds = patients.stream()
                .map(patient -> patient.getId())
                .toList();

        // 3. 환자 ID를 기반으로 간병인 목록 조회
        List<CaregiverPatient> caregivers = caregiverPatientRepository.findAllByPatientIdIn(patientIds);

        //4.
        return caregivers.stream()
                // caregiverId로 그룹화
                .collect(Collectors.groupingBy(cgPatient -> cgPatient.getCaregiver().getId()))
                .entrySet().stream()
                .map(entry -> {
                    Long caregiverId = entry.getKey();
                    String caregiverName = entry.getValue().get(0).getCaregiver().getName(); // caregiver 이름은 환자마다 같다고 가정
                    List<String> patientNames = entry.getValue().stream()
                            .map(cgPatient -> cgPatient.getPatient().getName())
                            .collect(Collectors.toList());

                    return new CaregiverPatientResponse(caregiverName, caregiverId, patientNames);
                })
                .collect(Collectors.toList());
    }
}

