package careneighbors.caregiver;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CaregiverRepository extends JpaRepository<Caregiver, Long> {

    // 환자 ID로 간병인 목록 조회
    List<Caregiver> findByCaregiverPatientsPatientId(Long patientId);
}
