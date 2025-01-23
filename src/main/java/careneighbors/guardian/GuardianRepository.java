package careneighbors.guardian;

import careneighbors.caregiver.Caregiver;
import jakarta.validation.constraints.NotBlank;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface GuardianRepository extends JpaRepository<Guardian, Long> {
    boolean existsByResidentNumberOrPhoneNumber(String residentNumber, String phoneNumber);

    // 보호자 ID로 환자 목록 조회
    List<GuardianPatient> findByGuardianId(Long guardianId);

    // 환자 ID로 간병인 목록 조회
    List<Caregiver> findByPatientId(Long patientId);
}
