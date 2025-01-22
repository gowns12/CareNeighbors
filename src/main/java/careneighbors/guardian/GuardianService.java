package careneighbors.guardian;


import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GuardianService {

    private final GuardianRepository guardianRepository;

    public GuardianService(GuardianRepository guardianRepository) {
        this.guardianRepository = guardianRepository;
    }


    @Transactional
    public void createGuardian(CreateGuardianRequest request) {

        boolean guardianExists = guardianRepository.existsByResidentNumberOrPhoneNumber(
                request.ResidentNumber(), request.phoneNumber());

        if (guardianExists) {
            throw new IllegalArgumentException("이미 존재하는 보호자");
        }

        Guardian guardian = new Guardian(
                request.name(),
                request.ResidentNumber(),
                request.phoneNumber(),
                request.Location()
        );

        guardianRepository.save(guardian);
    }

    //보호자 전체 조회
    public List<GuardianResponse> findAllGuardians() {
        List<Guardian> guardians = guardianRepository.findAll();

        return guardians.stream()
                .map(guardian -> new GuardianResponse(
                        guardian.getName(),
                        guardian.getPhoneNumber(),
                        guardian.getResidentNumber(),
                        guardian.getLocation())
                ).toList();
    }


    //보호자 id로 특정 보호자 조회
    public GuardianResponse findGuardiansByGuardianId(Long guardianId) {
        Guardian guardian = guardianRepository.findById(guardianId)
                .orElseThrow(()->new IllegalArgumentException("없는보호자"));

        return new GuardianResponse(guardian.getName(), guardian.getPhoneNumber(),guardian.getResidentNumber(),guardian.getLocation());
    }


    @Transactional
    public void updateGuardianRequest(Long guardianId, UpdateGuardianRequest request) {
        Guardian guardian = guardianRepository.findById(guardianId)
                .orElseThrow(()->new IllegalArgumentException("없는보호자임"));
        guardian.setName(request.name());
        guardian.setResidentNumber(request.ResidentNumber());
        guardian.setPhoneNumber(request.phoneNumber());
        guardian.setLocation(request.location());
    }

    public void deleteByGuardianId(Long guardianId) {
        guardianRepository.deleteById(guardianId);
    }
}

