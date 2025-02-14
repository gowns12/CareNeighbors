package careneighbors.guardian;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface GuardianPatientRepository extends JpaRepository<GuardianPatient, Long> {
    List<GuardianPatient> findByPatientId(Long patientId);

    List<GuardianPatient> findAllByGuardianId(Long guardianId);

    // 보호자 ID로 환자 목록 조회
    List<GuardianPatient> findByGuardian_Id(Long guardianId);

}
